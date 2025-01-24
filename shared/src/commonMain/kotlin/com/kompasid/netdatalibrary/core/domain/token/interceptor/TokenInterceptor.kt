package com.kompasid.netdatalibrary.core.domain.token.interceptor

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.token.usecase.TokenUseCase

class TokenInterceptor(
    private val tokenUseCase: TokenUseCase
) {
    suspend fun <T> withValidToken(action: suspend (String) -> ApiResults<T, NetworkError>): ApiResults<T, NetworkError> {
        return when (val tokenResult = tokenUseCase.getValidToken()) {
            is Results.Success -> {
                val validToken = tokenResult.data
                action(validToken) // Jalankan HTTP request dengan token yang valid
            }

            is Results.Error -> {
                ApiResults.Error(tokenResult.error) // Return error jika gagal mendapatkan token
            }
        }
    }
}