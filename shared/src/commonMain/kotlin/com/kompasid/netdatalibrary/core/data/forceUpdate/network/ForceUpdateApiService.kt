package com.kompasid.netdatalibrary.core.data.forceUpdate.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.response.ForceUpdateResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ForceUpdateApiService(
    private val httpClient: HttpClient,
    private val apiEnvironment: ApiEnvironment
) : IForceUpdateApiService {
    suspend fun forceUpdate(): ApiResults<ForceUpdateResponse, NetworkError> {
        val url = apiEnvironment.getForceUpdateUrl()
        return safeCall<ForceUpdateResponse> {
            httpClient.get(url) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}