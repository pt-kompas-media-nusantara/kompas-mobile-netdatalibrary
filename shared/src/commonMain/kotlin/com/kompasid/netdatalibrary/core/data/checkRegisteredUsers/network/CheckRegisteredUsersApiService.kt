package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.onSuccess
import com.kompasid.netdatalibrary.base.network.responseToResult
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.base.validation.ValidationRules
import com.kompasid.netdatalibrary.base.validation.Validator
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.request.CheckRegisteredUsersRequest
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckVerifiedUserResponse
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.response.OSRecommendationResponse
import com.kompasid.netdatalibrary.core.data.osRecomendation.network.IOSRecomendationApiService
import com.kompasid.netdatalibrary.core.domain.token.interceptor.TokenInterceptor
import com.kompasid.netdatalibrary.helpers.TextValidator
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.network.UnresolvedAddressException


class CheckRegisteredUsersApiService(
    private val httpClient: HttpClient,
    private val apiEnvironment: ApiEnvironment,
    private val tokenInterceptor: TokenInterceptor,
) : ICheckRegisteredUsersApiService {

    private val emptyValidator = Validator(ValidationRules.emptyValidation)

    suspend fun checkRegisteredUsers(value: String): ApiResults<CheckVerifiedUserResponse, NetworkError> {
        val url = apiEnvironment.getCheckRegisteredUsersUrl()

        val emptyErrors = emptyValidator.validate(value)
        if (emptyErrors != null) {
            return ApiResults.Error(NetworkError.Technical(400, "Invalid: ${emptyErrors.joinToString()}"))
        }

        val result = tokenInterceptor.withValidToken { validToken ->
            val response = try {
                val type = TextValidator.detectType(value)
                val request = CheckRegisteredUsersRequest(type = type.value, value = value)

                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    setBody(request)
                    bearerAuth(validToken)
                }
            } catch (e: Exception) {
                return@withValidToken handleException(e)
            }

            // Success if 2xx or 409
            if (response.status.value in 200..299 || response.status.value == 409) {
                return@withValidToken try {
                    val body = response.body<CheckVerifiedUserResponse>()
                    ApiResults.Success(body)
                } catch (e: Exception) {
                    ApiResults.Error(NetworkError.Technical(response.status.value, "Failed to parse body: ${e.message}"))
                }
            }

            // Other error status
            return@withValidToken responseToResult(response)
        }

        return result
    }

    private fun handleException(e: Exception): ApiResults.Error<NetworkError> {
        return when (e) {
            is SocketTimeoutException -> ApiResults.Error(NetworkError.RequestTimeout)
            is UnresolvedAddressException -> ApiResults.Error(NetworkError.NoInternet)
            else -> ApiResults.Error(NetworkError.Unknown)
        }
    }
}
