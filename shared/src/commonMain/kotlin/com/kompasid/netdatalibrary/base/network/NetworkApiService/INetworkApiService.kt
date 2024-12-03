package com.kompasid.netdatalibrary.base.network.NetworkApiService

import com.kompasid.netdatalibrary.base.network.NetworkError
import io.ktor.http.HttpStatusCode

interface INetworkApiService {
    suspend fun fetchDataFromApi(url: String): Pair<Any, HttpStatusCode>
    fun mapHttpStatusToError(statusCode: Int): NetworkError
    fun mapExceptionToError(exception: Throwable): NetworkError
}

