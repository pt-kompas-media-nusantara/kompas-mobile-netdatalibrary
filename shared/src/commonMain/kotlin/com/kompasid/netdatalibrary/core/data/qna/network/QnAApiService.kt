package com.kompasid.netdatalibrary.core.data.qna.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.qna.dto.response.QnAResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType



class QnAApiService(
    private val httpClient: HttpClient,
) : IQnAApiService {
    override suspend fun qna(): ApiResults<QnAResponse, NetworkError> {
        return safeCall<QnAResponse> {
            httpClient.get(ApiConfig.QNA_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}


