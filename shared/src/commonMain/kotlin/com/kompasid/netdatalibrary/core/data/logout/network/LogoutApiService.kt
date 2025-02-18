package com.kompasid.netdatalibrary.core.data.logout.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.core.data.logout.dto.request.LogoutRequest
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class LogoutApiService(
    private val httpClient: HttpClient,
    private val settingsDataSource: SettingsDataSource,
) : ILogoutApiService {
    override suspend fun postLogout(): ApiResults<Unit, NetworkError> {
        return safeCall<Unit> {

            val request = LogoutRequest(
                settingsDataSource.load(KeySettingsType.REFRESH_TOKEN, "")
            )

            httpClient.post(ApiConfig.LOGOUT_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }
}


