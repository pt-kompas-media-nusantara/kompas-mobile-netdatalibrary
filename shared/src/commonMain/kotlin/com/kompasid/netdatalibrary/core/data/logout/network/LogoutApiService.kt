package com.kompasid.netdatalibrary.core.data.logout.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.core.data.logout.dto.request.LogoutRequest
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class LogoutApiService(
    private val httpClient: HttpClient,
    private val settingsHelper: SettingsHelper,
    private val apiEnvironment: ApiEnvironment,
) : ILogoutApiService {
    override suspend fun postLogout(): ApiResults<Unit, NetworkError> {
        val url = apiEnvironment.getLogoutUrl()
        val refreshToken = settingsHelper.get(KeySettingsType.REFRESH_TOKEN, "")
        val request = LogoutRequest(refreshToken)

        return safeCall<Unit> {
            httpClient.post(url) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                setBody(request)
            }
        }
    }
}


