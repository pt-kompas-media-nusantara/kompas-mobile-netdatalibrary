package com.kompasid.netdatalibrary.core.data.osRecomendation.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
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
) : IOSRecomendationApiService {
    suspend fun osRecommendation(): ApiResults<OSRecommendationResponse, NetworkError> {
        return safeCall<OSRecommendationResponse> {
            httpClient.get(ApiConfig.OS_RECOMENDATION) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}


