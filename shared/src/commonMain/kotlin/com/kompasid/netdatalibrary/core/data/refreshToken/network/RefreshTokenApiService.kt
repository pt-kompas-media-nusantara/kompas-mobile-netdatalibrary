package com.kompasid.netdatalibrary.core.data.refreshToken.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.base.response.BaseResponse
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.response.OldMyRubriksResponse
import com.kompasid.netdatalibrary.core.data.myRubriks.network.IMyRubriksApiService
import com.kompasid.netdatalibrary.core.data.refreshToken.dto.request.RefreshTokenRequest
import com.kompasid.netdatalibrary.core.data.refreshToken.dto.response.RefreshTokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.io.IOException


class RefreshTokenApiService(
    private val httpClient: HttpClient,
    private val settingsDataSource: SettingsDataSource
) : IRefreshTokenApiService {
    override suspend fun postRefreshToken(): ApiResults<RefreshTokenResponse, NetworkError> {
        return safeCall<RefreshTokenResponse> {
            val request = RefreshTokenRequest(
                settingsDataSource.getStringFlow(KeySettingsType.REFRESH_TOKEN).value ?: ""
            )

            httpClient.post(ApiConfig.REFRESH_TOKEN_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }
}



