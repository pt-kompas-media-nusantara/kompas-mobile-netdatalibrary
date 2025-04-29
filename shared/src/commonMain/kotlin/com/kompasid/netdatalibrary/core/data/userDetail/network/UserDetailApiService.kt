package com.kompasid.netdatalibrary.core.data.userDetail.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.userDetail.dto.response.OldUserDetailResponse
import com.kompasid.netdatalibrary.core.domain.token.interceptor.TokenInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UserDetailApiService(
    private val httpClient: HttpClient,
    private val tokenInterceptor: TokenInterceptor,
    private val apiEnvironment: ApiEnvironment
) : IUserDetailApiService {
    override suspend fun getUserDetail(): ApiResults<OldUserDetailResponse, NetworkError> {
        val url = apiEnvironment.getUserDetailUrl()

        return tokenInterceptor.withValidToken { validToken ->
            safeCall<OldUserDetailResponse> {
                httpClient.get(url) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(validToken)
                }
            }
        }
    }
}
