package com.kompasid.netdatalibrary.core.data.osRecomendation.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiEnvironment
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.response.OSRecommendationResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class OSRecomendationApiService(
    private val httpClient: HttpClient,
    private val apiEnvironment: ApiEnvironment
) : IOSRecomendationApiService {
    suspend fun osRecommendation(): ApiResults<OSRecommendationResponse, NetworkError> {
        val url = apiEnvironment.getOsRecommendationUrl()
        return safeCall<OSRecommendationResponse> {
            httpClient.get(url) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}

