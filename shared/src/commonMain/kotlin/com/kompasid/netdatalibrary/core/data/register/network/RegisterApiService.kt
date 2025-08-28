package com.kompasid.netdatalibrary.core.data.register.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.base.validation.ValidationRules
import com.kompasid.netdatalibrary.base.validation.Validator
import com.kompasid.netdatalibrary.core.data.register.dto.request.RegisterRequest
import com.kompasid.netdatalibrary.core.data.register.dto.response.RegisterResponse
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class RegisterApiService(
    private val httpClient: HttpClient,
    private val settingsHelper: SettingsHelper,
    private val apiEnvironment: ApiEnvironment,
) : IRegisterApiService {

    private val emailValidator = Validator(ValidationRules.emailValidation)
    private val passwordValidator = Validator(ValidationRules.passwordValidation)

    suspend fun registerByEmail(email: String, firstName: String, lastName: String, password: String): ApiResults<RegisterResponse, NetworkError> {
        val url = apiEnvironment.getRegisterByEmailUrl()

        val emailErrors = emailValidator.validate(email)
        if (emailErrors != null) {
            return ApiResults.Error(NetworkError.Technical(400, "Invalid email: ${emailErrors.joinToString()}"))
        }

        val passwordErrors = passwordValidator.validate(password)
        if (passwordErrors != null) {
            return ApiResults.Error(NetworkError.Technical(400, "Invalid password: ${passwordErrors.joinToString()}"))
        }

        return safeCall<RegisterResponse> {
            val request = RegisterRequest(
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName,
                device = settingsHelper.get(KeySettingsType.DEVICE, ""),
                deviceType = settingsHelper.get(KeySettingsType.DEVICE_TYPE, ""),
                docReferrer = "iOS", //settingsHelper.get(KeySettingsType.DOC_REFERRER, ""),
            )

            httpClient.post(url) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }
}