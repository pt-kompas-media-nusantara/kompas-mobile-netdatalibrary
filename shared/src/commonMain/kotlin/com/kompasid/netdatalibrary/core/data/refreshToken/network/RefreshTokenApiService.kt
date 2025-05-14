package com.kompasid.netdatalibrary.core.data.refreshToken.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.data.refreshToken.dto.request.RefreshTokenRequest
import com.kompasid.netdatalibrary.core.data.refreshToken.dto.response.RefreshTokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class RefreshTokenApiService(
    private val httpClient: HttpClient,
    private val settingsHelper: SettingsHelper
) : IRefreshTokenApiService {
    override suspend fun postRefreshToken(): ApiResults<RefreshTokenResponse, NetworkError> {
        return safeCall<RefreshTokenResponse> {
            val request = RefreshTokenRequest(settingsHelper.get(KeySettingsType.REFRESH_TOKEN , ""))

            httpClient.post(ApiConfig.REFRESH_TOKEN_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }
}



