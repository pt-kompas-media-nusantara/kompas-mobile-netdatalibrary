package com.kompasid.netdatalibrary.core.data.login.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.base.validation.ValidationRules
import com.kompasid.netdatalibrary.base.validation.Validator
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginAppleRequest
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.login.dto.request.LoginGoogleRequest
import com.kompasid.netdatalibrary.core.data.login.dto.response.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class LoginApiService(
    private val httpClient: HttpClient,
) : ILoginApiService {

    private val emailValidator = Validator(ValidationRules.emailValidation)
    private val passwordValidator = Validator(ValidationRules.passwordValidation)

    suspend fun loginByEmail(request: LoginEmailRequest): ApiResults<LoginResponse, NetworkError> {
        return safeCall<LoginResponse> {

            val emailErrors = emailValidator.validate(request.email)
            if (emailErrors != null) {
                return ApiResults.Error(NetworkError.Technical(400, "Invalid email: ${emailErrors.joinToString()}"))
            }

            val passwordErrors = passwordValidator.validate(request.password)
            if (passwordErrors != null) {
                return ApiResults.Error(NetworkError.Technical(400, "Invalid password: ${passwordErrors.joinToString()}"))
            }

            httpClient.post(ApiConfig.LOGIN_BY_EMAIL_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }

    suspend fun loginByGoogle(request: LoginGoogleRequest): ApiResults<LoginResponse, NetworkError> {
        return safeCall<LoginResponse> {
            httpClient.post(ApiConfig.LOGIN_BY_GOOGLE_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }

    suspend fun loginByApple(request: LoginAppleRequest): ApiResults<LoginResponse, NetworkError> {
        return safeCall<LoginResponse> {
            httpClient.post(ApiConfig.LOGIN_BY_APPLE_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }
}

