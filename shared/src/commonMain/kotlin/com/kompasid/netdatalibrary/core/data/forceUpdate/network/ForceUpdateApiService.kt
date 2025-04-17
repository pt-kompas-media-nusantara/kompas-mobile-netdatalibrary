package com.kompasid.netdatalibrary.core.data.forceUpdate.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.response.ForceUpdateResponse
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.response.OSRecomendationResponse
import com.kompasid.netdatalibrary.core.data.osRecomendation.network.IOSRecomendationApiService
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ForceUpdateApiService(
    private val httpClient: HttpClient,
) : IForceUpdateApiService {
    suspend fun forceUpdate(): ApiResults<ForceUpdateResponse, NetworkError> {
        return safeCall<ForceUpdateResponse> {
            httpClient.get(ApiConfig.FORCE_UPDATE) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}