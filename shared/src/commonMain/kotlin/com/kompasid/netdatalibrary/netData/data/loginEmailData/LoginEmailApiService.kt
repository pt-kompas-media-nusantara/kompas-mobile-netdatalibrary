package com.kompasid.netdatalibrary.netData.data.loginEmailData

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.response.BaseResponse
import com.kompasid.netdatalibrary.base.validation.ValidationRules
import com.kompasid.netdatalibrary.base.validation.Validator
import com.kompasid.netdatalibrary.netData.data.loginEmailData.dto.LoginEmailRequest
import com.kompasid.netdatalibrary.netData.data.loginEmailData.dto.LoginEmailResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.io.IOException


class LoginEmailApiService(
    private val httpClient: HttpClient
) {

    suspend fun postLoginEmail(request: LoginEmailRequest): ApiResults<LoginEmailResponse, NetworkError> {
        try {

            val emailValidator = Validator(ValidationRules.emptyValidation)
            val passwordValidator = Validator(ValidationRules.emptyValidation)

            val emailErrors = emailValidator.validate(request.email)
            if (emailErrors != null) {
                return ApiResults.Error(
                    NetworkError.Technical(
                        400,
                        "Email must not be empty"
                    )
                )
            }

            val passwordErrors = passwordValidator.validate(request.password)
            if (passwordErrors != null) {
                return ApiResults.Error(
                    NetworkError.Technical(
                        400,
                        "Password must not be empty nurirppan"
                    )
                )
            }

            val httpResponse: HttpResponse =
                httpClient.post("https://api.kompas.id/account/api/v2/login/email") {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    setBody(request)
                }

            val statusCode = httpResponse.status.value

            val response: BaseResponse<LoginEmailResponse> = httpResponse.body()

            when (response.code) {
                200 -> {
                    response.data?.let {
                        return ApiResults.Success(it)
                    } ?: return ApiResults.Error(
                        NetworkError.Technical(
                            response.code ?: 500,
                            response.message ?: ""
                        )
                    )
                }

                400 -> return ApiResults.Error(
                    NetworkError.Technical(
                        response.code ?: 500,
                        response.message ?: ""
                    )
                )

                401 -> return ApiResults.Error(NetworkError.Unauthorized)
                404 -> return ApiResults.Error(NetworkError.NotFound)
                in 500..599 -> return ApiResults.Error(NetworkError.ServerError)
                else -> return ApiResults.Error(
                    NetworkError.Technical(
                        response.code ?: 500,
                        response.message ?: ""
                    )
                )
            }

        } catch (e: RedirectResponseException) {
            // 3xx responses (redirects)
            return ApiResults.Error(NetworkError.Error(e))
        } catch (e: ClientRequestException) {
            // 4xx responses (client errors)
            return ApiResults.Error(NetworkError.Error(e))
        } catch (e: ServerResponseException) {
            // 5xx responses (server errors)
            return ApiResults.Error(NetworkError.Error(e))
        } catch (e: IOException) {
            // Jaringan tidak tersedia atau permintaan mengalami timeout
            return ApiResults.Error(NetworkError.Error(e))
        } catch (e: Exception) {
            // Error lain, misalnya masalah serialisasi
            return ApiResults.Error(NetworkError.Error(e))
        }
    }
}