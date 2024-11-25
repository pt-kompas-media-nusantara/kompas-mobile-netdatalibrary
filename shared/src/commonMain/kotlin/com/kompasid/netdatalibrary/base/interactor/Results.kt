package com.kompasid.netdatalibrary.base.interactor

// Result menggunakan NetworkError sebagai pengganti Error
sealed interface Results<out D, out E: NetworkError> {
    data class Success<out D>(val data: D): Results<D, Nothing>
    data class Error<out E: NetworkError>(val error: E): Results<Nothing, E>
}

// Function untuk memetakan Result menjadi tipe lain
inline fun <T, E: NetworkError, R> Results<T, E>.map(map: (T) -> R): Results<R, E> {
    return when(this) {
        is Results.Error -> Results.Error(error)
        is Results.Success -> Results.Success(map(data))
    }
}

// Function untuk mengubah Result menjadi EmptyResult
fun <T, E: NetworkError> Results<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }
}

// Callback untuk success handling
inline fun <T, E: NetworkError> Results<T, E>.onSuccess(action: (T) -> Unit): Results<T, E> {
    return when(this) {
        is Results.Error -> this
        is Results.Success -> {
            action(data)
            this
        }
    }
}

// Callback untuk error handling
inline fun <T, E: NetworkError> Results<T, E>.onError(action: (E) -> Unit): Results<T, E> {
    return when(this) {
        is Results.Error -> {
            action(error)
            this
        }
        is Results.Success -> this
    }
}

// Menggunakan typealias untuk EmptyResult
typealias EmptyResult<E> = Results<Unit, E>
