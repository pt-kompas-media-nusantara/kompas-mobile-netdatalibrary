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

//TODO:Wahyu - Gamasalah mas selagi itu berkaitan dengan network, gaperlu dipindah karena file loc nya udah sesuai di base network

// nurirppan__
// maksud kode ini jalannya gimana ya ?. gue bingung kode return responseToResult(response) kapan jalannya

//TODO: Wahyu - Dia akan execute httpcall dari consumernya,
// lalu misal ada error dia akan masuk ke catch dan langsung return
// tapi kalau engga hasil dari http call itu akan masuk response dan akan di return melalui responseToResult(response)
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

// TODO: Wahyu - Betul ini langsung pake status code karena kedepan klo ga salah memang mau pake status code gapake yang dari body lagi
suspend inline fun <reified T> responseToResult(response: HttpResponse): ApiResults<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                ApiResults.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                // nurirppan__
                // masuk kesini ketika apa aja yu ?
                // TODO: Wahyu - Misal JSON parsenya error mas atau ga sesuai, errornya bisa disesuaiin karena kotlin case sensitif
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