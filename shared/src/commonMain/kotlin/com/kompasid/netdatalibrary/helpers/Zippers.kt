package com.kompasid.netdatalibrary.helpers

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results

// Satu alias biar pendek & konsisten
typealias AppResult<T> = Results<T, NetworkError>

// zip2 sebagai dasar
inline fun <A, B, R> zip(
    ra: AppResult<A>,
    rb: AppResult<B>,
    combine: (A, B) -> R
): AppResult<R> = when {
    ra is Results.Success && rb is Results.Success ->
        Results.Success(combine(ra.data, rb.data))
    ra is Results.Error -> Results.Error(ra.error)
    rb is Results.Error -> Results.Error(rb.error)
    else -> Results.Error(NetworkError.ServerError)
}

// zip3 berbasis zip2
inline fun <A, B, C, R> zip3(
    ra: AppResult<A>,
    rb: AppResult<B>,
    rc: AppResult<C>,
    combine: (A, B, C) -> R
): AppResult<R> =
    zip(ra, rb) { a, b -> a to b }
        .let { pairRes ->
            when (pairRes) {
                is Results.Success -> zip(Results.Success(pairRes.data), rc) { (a, b), c -> combine(a, b, c) }
                is Results.Error   -> Results.Error(pairRes.error)
            }
        }
