package com.kompasid.netdatalibrary.core.data.myRubriks.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.request.SaveMyRubrikRequest
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.response.OldMyRubriksResponse
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.response.SaveMyRubrikResponse
import com.kompasid.netdatalibrary.core.domain.token.interceptor.TokenInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType


class MyRubriksApiService(
    private val httpClient: HttpClient,
    private val tokenInterceptor: TokenInterceptor,
) : IMyRubriksApiService {
    override suspend fun getRubrikList(): ApiResults<OldMyRubriksResponse, NetworkError> {
        return tokenInterceptor.withValidToken { validToken ->
            safeCall<OldMyRubriksResponse> {
                httpClient.get(ApiConfig.MY_RUBRIKS_URL) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(validToken)
                }
            }
        }
    }

    suspend fun saveMyRubriks(request: SaveMyRubrikRequest): ApiResults<SaveMyRubrikResponse, NetworkError> {
        return tokenInterceptor.withValidToken { validToken ->
            safeCall<SaveMyRubrikResponse> {
                httpClient.post(ApiConfig.SAVE_MY_RUBRIKS_URL) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(validToken)
                }
            }
        }

    }
}



