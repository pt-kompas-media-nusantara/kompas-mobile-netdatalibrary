package com.kompasid.netdatalibrary.core.data.countries.network

import com.kompasid.netdatalibrary.base.network.ApiConfig
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.safeCall
import com.kompasid.netdatalibrary.core.data.countries.dto.response.CountriesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType


class CountriesApiService(
    private val httpClient: HttpClient
) : ICountriesApiService {
    override suspend fun countries(): ApiResults<CountriesResponse, NetworkError> {
        return safeCall<CountriesResponse> {
            httpClient.get(ApiConfig.COUNTRIES_URL) {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}