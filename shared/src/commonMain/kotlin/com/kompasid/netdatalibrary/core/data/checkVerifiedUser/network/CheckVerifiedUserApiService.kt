package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.request.CheckVerifiedUserRequest
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.response.CheckVerifiedUserResponse
import com.kompasid.netdatalibrary.core.data.logout.dto.request.LogoutRequest
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.request.SaveMyRubrikRequest
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.response.SaveMyRubrikResponse
import com.kompasid.netdatalibrary.core.data.userDetail.dto.response.OldUserDetailResponse
import com.kompasid.netdatalibrary.core.domain.token.interceptor.TokenInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
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
    private val tokenInterceptor: TokenInterceptor,
    private val settingsHelper: SettingsHelper
) : ICheckVerifiedUserApiService {

    suspend fun postCheckVerifiedUser(): ApiResults<CheckVerifiedUserResponse, NetworkError> {
        return tokenInterceptor.withValidToken { validToken ->
            safeCall<CheckVerifiedUserResponse> {
                val loginType = settingsHelper.get(KeySettingsType.LOGIN_TYPE, "")
                val email = settingsHelper.get(KeySettingsType.EMAIL, "")

                val request = CheckVerifiedUserRequest(
                    type = loginType,
                    value = email,
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