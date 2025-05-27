package com.kompasid.netdatalibrary.core.data.generalContent.network

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.core.data.generalContent.model.dto.GeneralContentResponse
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType


// https://cdn-content.kompas.id/mobile/json/generalContent.json   | app icon
// https://private-d6360b-hariankompasios.apiary-mock.com/generalContent
class GeneralContentApiService(
    private val httpClient: HttpClient
) : IGeneralContentApiService {
    override suspend fun getGeneralContent(customUrl: String): ApiResults<GeneralContentResponse, NetworkError> {
        return safeCall<GeneralContentResponse> {
            httpClient.get(customUrl.ifBlank { ApiConfig.GENERAL_URL }) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}
