package com.kompasid.netdatalibrary.core.data.otp.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.base.validation.ValidationRules
import com.kompasid.netdatalibrary.base.validation.Validator
import com.kompasid.netdatalibrary.core.data.otp.dto.request.SendOTPRequest
import com.kompasid.netdatalibrary.core.data.otp.dto.request.VerifyOTPRequest
import com.kompasid.netdatalibrary.core.data.otp.dto.response.SendOTPResponse
import com.kompasid.netdatalibrary.core.data.otp.dto.response.VerifyOTPResponse
import com.kompasid.netdatalibrary.core.domain.token.interceptor.TokenInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class OTPApiService(
    private val httpClient: HttpClient,
    private val apiEnvironment: ApiEnvironment,
    private val tokenInterceptor: TokenInterceptor,
    private val settingsHelper: SettingsHelper
) : IOTPApiService {

    suspend fun sendOTP(countryCode: String, phoneNumber: String): ApiResults<SendOTPResponse, NetworkError> {
        val phoneValidator = Validator(ValidationRules.phoneValidation)

        val url = apiEnvironment.getSendOTPUrl()

        val phoneErrors = phoneValidator.validate(phoneNumber)
        if (phoneErrors != null) {
            return ApiResults.Error(NetworkError.Technical(400, "Invalid Phone Number: ${phoneErrors.joinToString()}"))
        }

        return tokenInterceptor.withValidToken { validToken ->
            safeCall<SendOTPResponse> {

                val request = SendOTPRequest(
                    countryCode = countryCode,
                    flag = 2, // ada 1 dan 2, bedanya apa ya ? nurirppan_ : tanya pak bayu
                    phoneNumber = phoneNumber,
                )

                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    setBody(request)
                    bearerAuth(validToken)
                }
            }
        }
    }

    suspend fun verifyOTP(countryCode: String, phoneNumber: String, otp: String): ApiResults<VerifyOTPResponse, NetworkError> {

        val url = apiEnvironment.getVerifyOTPUrl()
        val device = settingsHelper.get(KeySettingsType.DEVICE, "")
        val deviceType = settingsHelper.get(KeySettingsType.DEVICE_TYPE, "")
        val docReferrer = settingsHelper.get(KeySettingsType.DOC_REFERRER, "")

        return tokenInterceptor.withValidToken { validToken ->
            safeCall<VerifyOTPResponse> {

                val request = VerifyOTPRequest(
                    countryCode = countryCode,
                    device = device,
                    deviceType = deviceType,
                    docReferrer = docReferrer,
                    otp = otp,
                    phoneNumber = phoneNumber,
                )

                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    setBody(request)
                    bearerAuth(validToken)
                }
            }
        }
    }
}