package com.kompasid.netdatalibrary.core.data.loginEmail.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.base.response.BaseResponse
import com.kompasid.netdatalibrary.base.validation.ValidationRules
import com.kompasid.netdatalibrary.base.validation.Validator
import com.kompasid.netdatalibrary.core.data.loginEmail.models.dto.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginEmail.models.dto.LoginEmailResponse
import com.kompasid.netdatalibrary.core.data.qna.dto.response.QnAResponse
import com.kompasid.netdatalibrary.core.data.qna.network.IQnAApiService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.io.IOException



class LoginEmailApiService(
    private val httpClient: HttpClient,
) : ILoginEmailApiService {

    private val emailValidator = Validator(ValidationRules.emailValidation)
    private val passwordValidator = Validator(ValidationRules.passwordValidation)

    override suspend fun postLoginEmail(request: LoginEmailRequest): ApiResults<LoginEmailResponse, NetworkError> {
        return safeCall<LoginEmailResponse> {

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
}

