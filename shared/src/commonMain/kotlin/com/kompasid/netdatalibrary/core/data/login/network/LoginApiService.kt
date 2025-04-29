package com.kompasid.netdatalibrary.core.data.login.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.base.validation.ValidationRules
import com.kompasid.netdatalibrary.base.validation.Validator
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.request.CheckRegisteredUsersRequest
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckVerifiedUserResponse
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginAppleRequest
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginByPurchaseTokenRequest
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginGoogleRequest
import com.kompasid.netdatalibrary.core.data.login.dto.response.LoginResponse
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.TextValidator
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class LoginApiService(
    private val httpClient: HttpClient,
    private val apiEnvironment: ApiEnvironment,
    private val settingsHelper: SettingsHelper,
) : ILoginApiService {

    private val emailValidator = Validator(ValidationRules.emailValidation)
    private val passwordValidator = Validator(ValidationRules.passwordValidation)

    suspend fun loginByEmail(email: String, password: String): ApiResults<LoginResponse, NetworkError> {
        val url = apiEnvironment.getLoginByEmailUrl()
        val emailErrors = emailValidator.validate(email)
        if (emailErrors != null) {
            return ApiResults.Error(NetworkError.Technical(400, "Invalid email: ${emailErrors.joinToString()}"))
        }

        val passwordErrors = passwordValidator.validate(password)
        if (passwordErrors != null) {
            return ApiResults.Error(NetworkError.Technical(400, "Invalid password: ${passwordErrors.joinToString()}"))
        }

        val request = LoginEmailRequest(
            email = email,
            password = password,
            device = settingsHelper.get(KeySettingsType.DEVICE, ""),
            deviceType = settingsHelper.get(KeySettingsType.DEVICE_TYPE, ""),
        )

        return safeCall<LoginResponse> {
            httpClient.post(url) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }

    suspend fun loginByGoogle(accessToken: String, state: String): ApiResults<LoginResponse, NetworkError> {
        val url = apiEnvironment.getLoginByGoogleUrl()

        val request = LoginGoogleRequest(
            accessToken = accessToken,
            device = settingsHelper.get(KeySettingsType.DEVICE, ""),
            deviceType = settingsHelper.get(KeySettingsType.DEVICE_TYPE, ""),
            docReferrer = "iOS", // KeySettingsType.DOC_REFERRER
            state = state, // state ini isinya apa ya ?
        )

        return safeCall<LoginResponse> {
            httpClient.post(url) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }

    suspend fun loginByApple(accessToken: String): ApiResults<LoginResponse, NetworkError> {
        val url = apiEnvironment.getLoginByAppleUrl()

        val request = LoginAppleRequest(
            accessToken = accessToken,
            device = settingsHelper.get(KeySettingsType.DEVICE, ""),
            deviceType = settingsHelper.get(KeySettingsType.DEVICE_TYPE, ""),
            docReferrer = "iOS", // KeySettingsType.DOC_REFERRER
        )

        return safeCall<LoginResponse> {
            httpClient.post(url) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }

    suspend fun loginByPurchaseToken(): ApiResults<LoginResponse, NetworkError> {
        val url = apiEnvironment.getLoginByPurchaseTokenUrl()
        val oriTrxId: List<String> = settingsHelper.get(KeySettingsType.ORIGINAL_TRANSACTION_ID, emptyList())

        val request = LoginByPurchaseTokenRequest(
            token = oriTrxId.last()
        )

        return safeCall<LoginResponse> {
            httpClient.post(url) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }
}

