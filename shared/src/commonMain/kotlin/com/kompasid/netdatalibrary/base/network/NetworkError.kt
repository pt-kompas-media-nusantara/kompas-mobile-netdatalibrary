package com.kompasid.netdatalibrary.base.network

// Membuat interface CustomError untuk menggantikan Error
interface CustomError

// Sealed class untuk berbagai jenis error jaringan
sealed class NetworkError : CustomError {
    object RequestTimeout : NetworkError()
    object Unauthorized : NetworkError()
    object NoInternet : NetworkError()
    object ServerError : NetworkError()
    object NotFound : NetworkError()

    // Tambahkan class untuk error yang menerima kode dan pesan
    data class Technical(val code: Int, val message: String) : NetworkError()

    data class Error(val throwable: Throwable) : NetworkError()


    data class General<T>(val value: T) : NetworkError()
}

/*
Contoh Penggunaan
val genericErrorString = NetworkError.General("Terjadi kesalahan umum")
val genericErrorInt = NetworkError.General(404)
val genericErrorData = NetworkError.General(mapOf("key" to "value"))

fun handleNetworkError(error: NetworkError) {
    when (error) {
        is NetworkError.RequestTimeout -> println("Request Timeout")
        is NetworkError.Unauthorized -> println("Unauthorized Access")
        is NetworkError.NoInternet -> println("No Internet Connection")
        is NetworkError.General<*> -> {
            when (val value = error.value) {
                is String -> println("General Error with message: $value")
                is Int -> println("General Error with code: $value")
                is Map<*, *> -> println("General Error with data: $value")
                else -> println("General Error with unknown type")
            }
        }
        else -> println("Other Network Error")
    }
}
*/