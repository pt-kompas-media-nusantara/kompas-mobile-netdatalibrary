package com.kompasid.netdatalibrary.base.network

import com.kompasid.netdatalibrary.base.network.NetworkApiService.INetworkApiService
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

// nurirppan__
// kalau safecall nya mau gue pake buat yang lain gimana ? apakah kode di bawah ini harus di pindahin di base code atau di jadikan base kode?


// nurirppan__
// maksud kode ini jalannya gimana ya ?. gue bingung kode return responseToResult(response) kapan jalannya
suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): ApiResults<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return ApiResults.Error(NetworkError.RequestTimeout)
    } catch (e: UnresolvedAddressException) {
        return ApiResults.Error(NetworkError.NoInternet)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return ApiResults.Error(NetworkError.Unknown)
    }

    return responseToResult(response)
}

// nurirppan__
// eh ini langsung pake status code ? gue sengaja buat masih pakai response code biar cepet, tapi nggak apa
// biar kita push aja temen temen BE untuk perbaiki
suspend inline fun <reified T> responseToResult(response: HttpResponse): ApiResults<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                ApiResults.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                // nurirppan__
                // masuk kesini ketika apa aja yu ?
                ApiResults.Error(NetworkError.ServerError)
            }
        }

        401 -> ApiResults.Error(NetworkError.Unauthorized)
        404 -> ApiResults.Error(NetworkError.NotFound)
        in 400..499 -> ApiResults.Error(
            NetworkError.Technical(
                response.status.value,
                response.status.description
            )
        )

        in 500..599 -> ApiResults.Error(NetworkError.ServerError)
        else -> ApiResults.Error(NetworkError.Unknown)
    }
}