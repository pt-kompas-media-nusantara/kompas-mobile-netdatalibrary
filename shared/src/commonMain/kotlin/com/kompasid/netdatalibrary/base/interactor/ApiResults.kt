package com.kompasid.netdatalibrary.base.interactor

// ApiResult menggunakan CustomError sebagai pengganti Error
sealed interface ApiResults<out D, out E : CustomError> {
    data class Success<out D>(val data: D) : ApiResults<D, Nothing>
    data class Error<out E : CustomError>(val error: E) : ApiResults<Nothing, E>
}

// Function untuk memetakan ApiResult menjadi tipe lain
inline fun <T, E : CustomError, R> ApiResults<T, E>.map(map: (T) -> R): ApiResults<R, E> {
    return when (this) {
        is ApiResults.Error -> ApiResults.Error(error)
        is ApiResults.Success -> ApiResults.Success(map(data))
    }
}

// Function untuk mengubah ApiResult menjadi EmptyApiResult
fun <T, E : CustomError> ApiResults<T, E>.asEmptyDataResult(): EmptyApiResult<E> {
    return map { }
}

// Callback untuk success handling
inline fun <T, E : CustomError> ApiResults<T, E>.onSuccess(action: (T) -> Unit): ApiResults<T, E> {
    return when (this) {
        is ApiResults.Error -> this
        is ApiResults.Success -> {
            action(data)
            this
        }
    }
}

// Callback untuk error handling
inline fun <T, E : CustomError> ApiResults<T, E>.onError(action: (E) -> Unit): ApiResults<T, E> {
    return when (this) {
        is ApiResults.Error -> {
            action(error)
            this
        }

        is ApiResults.Success -> this
    }
}

// Menggunakan typealias untuk EmptyApiResult
typealias EmptyApiResult<E> = ApiResults<Unit, E>
