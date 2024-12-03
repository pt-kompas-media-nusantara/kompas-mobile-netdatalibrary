package com.kompasid.netdatalibrary.base.network.NetworkApiService

import com.kompasid.netdatalibrary.base.network.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType


class NetworkApiService(private val httpClient: HttpClient) : INetworkApiService {

    override suspend fun fetchDataFromApi(url: String): Pair<Any, HttpStatusCode> {
        val response: HttpResponse = httpClient.get(url) {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        return Pair(response.body(), response.status)
    }

    override fun mapHttpStatusToError(statusCode: Int): NetworkError {
        return when (statusCode) {
            401 -> NetworkError.Unauthorized
            404 -> NetworkError.NotFound
            in 500..599 -> NetworkError.ServerError
            else -> NetworkError.Technical(statusCode, "Unexpected error")
        }
    }

    override fun mapExceptionToError(exception: Throwable): NetworkError {
        return when (exception) {
            is RedirectResponseException -> NetworkError.Error(exception) // 3xx
            is ClientRequestException -> NetworkError.Error(exception)    // 4xx
            is ServerResponseException -> NetworkError.Error(exception)  // 5xx
            is kotlinx.io.IOException -> NetworkError.NoInternet         // Network issue
            else -> NetworkError.Error(exception)                       // General exceptions
        }
    }
}


