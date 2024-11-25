package com.kompasid.netdatalibrary.netData.domain.tokenDomain

import com.kompasid.netdatalibrary.base.interactor.ApiResults
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.interactor.Results

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