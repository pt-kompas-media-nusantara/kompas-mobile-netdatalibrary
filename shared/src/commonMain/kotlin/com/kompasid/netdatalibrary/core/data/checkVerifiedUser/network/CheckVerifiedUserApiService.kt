package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.request.CheckVerifiedUserRequest
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.response.CheckVerifiedUserResponse
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.request.SaveMyRubrikRequest
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.response.SaveMyRubrikResponse
import com.kompasid.netdatalibrary.core.data.userDetail.dto.response.OldUserDetailResponse
import com.kompasid.netdatalibrary.core.domain.token.interceptor.TokenInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CheckVerifiedUserApiService(
    private val httpClient: HttpClient,
    private val tokenInterceptor: TokenInterceptor
) : ICheckVerifiedUserApiService {

    override suspend fun postCheckVerifiedUser(request: CheckVerifiedUserRequest): ApiResults<CheckVerifiedUserResponse, NetworkError> {
        return tokenInterceptor.withValidToken { validToken ->
            safeCall<CheckVerifiedUserResponse> {
                httpClient.post(ApiConfig.CHECK_VERIFIED_USER_URL) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(validToken)
                }
            }
        }
    }
}