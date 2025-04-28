package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.request.CheckRegisteredUsersRequest
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckVerifiedUserResponse
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.response.OSRecommendationResponse
import com.kompasid.netdatalibrary.core.data.osRecomendation.network.IOSRecomendationApiService
import com.kompasid.netdatalibrary.core.domain.token.interceptor.TokenInterceptor
import com.kompasid.netdatalibrary.helpers.TextValidator
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class CheckRegisteredUsersApiService(
    private val httpClient: HttpClient,
    private val apiEnvironment: ApiEnvironment,
    private val tokenInterceptor: TokenInterceptor,
) : ICheckRegisteredUsersApiService {

    suspend fun checkRegisteredUsers(value: String): ApiResults<CheckVerifiedUserResponse, NetworkError> {
        val url = apiEnvironment.getCheckRegisteredUsersUrl()
        return tokenInterceptor.withValidToken { validToken ->
            safeCall<CheckVerifiedUserResponse> {

                val type = TextValidator.detectType(value)

                val request = CheckRegisteredUsersRequest(
                    type = type.value, // email | phoneNumber
                    value = value,
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

/*
class CheckRegisteredUsersApiService(
    private val httpClient: HttpClient,
    private val tokenInterceptor: TokenInterceptor,
) : ICheckRegisteredUsersApiService {

    suspend fun checkRegisteredUsers(value: String): ApiResults<CheckVerifiedUserResponse, NetworkError> {
        return tokenInterceptor.withValidToken { validToken ->
            safeCall<CheckVerifiedUserResponse> {

                val type = TextValidator.detectType(value)

                val request = CheckRegisteredUsersRequest(
                    type = type.value, // email | phoneNumber
                    value = value,
                )

                httpClient.post(ApiConfig.CHECK_VERIFIED_USER_URL) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    setBody(request)
                    bearerAuth(validToken)
                }
            }
        }
    }
}
*/
