package com.kompasid.netdatalibrary.core.data.userHistoryMembership.network


import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response.unitMembership.UserMembershipResponse
import com.kompasid.netdatalibrary.core.data.userMembershipHistoryData.dto.UserHistoryMembershipOldResponse
import com.kompasid.netdatalibrary.core.domain.token.interceptor.TokenInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType


class UserMembershipApiService(
    private val httpClient: HttpClient,
    private val tokenInterceptor: TokenInterceptor,
    private val apiEnvironment: ApiEnvironment
) : IUserHistoryMembershipApiService {
    // ini sepertinya versi lama, harus konfirmasi ke bayu
    suspend fun getUserHistoryMembershipOld(): ApiResults<UserHistoryMembershipOldResponse, NetworkError> {
        val url = apiEnvironment.getUserHistoryMembershipOldUrl()

        return tokenInterceptor.withValidToken { validToken ->
            safeCall<UserHistoryMembershipOldResponse> {
                httpClient.get(url) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(validToken)
                }
            }

        }
    }

    // ini sepertinya versi baru, harus konfirmasi ke bayu
    suspend fun userMembership(): ApiResults<UserMembershipResponse, NetworkError> {
        val url = apiEnvironment.getUserMembershipUrl()

        return tokenInterceptor.withValidToken { validToken ->
            safeCall<UserMembershipResponse> {
                httpClient.get(url) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(validToken)
                }
            }

        }
    }
    
    // ini sepertinya versi baru, harus konfirmasi ke bayu
    suspend fun userMembership(): ApiResults<UserMembershipResponse, NetworkError> {
        val url = apiEnvironment.getUserMembershipUrl()

        return tokenInterceptor.withValidToken { validToken ->
            safeCall<UserMembershipResponse> {
                httpClient.get(url) {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(validToken)
                }
            }

        }
    }



    /// tidak di pakai karna sudah ada di api history membership
//    suspend fun getUserMembership(): ApiResults<UserMembershipResponse, NetworkError> {
//        return tokenInterceptor.withValidToken { validToken ->
//            safeCall<UserMembershipResponse> {
//                httpClient.get(ApiConfig.USER_MEMBERSHIP_URL) {
//                    contentType(ContentType.Application.Json)
//                    accept(ContentType.Application.Json)
//                    bearerAuth(validToken)
//                }
//            }
//
//        }
//    }
}
