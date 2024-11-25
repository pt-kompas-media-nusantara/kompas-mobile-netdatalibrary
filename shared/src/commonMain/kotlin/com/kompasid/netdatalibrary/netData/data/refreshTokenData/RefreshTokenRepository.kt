package com.kompasid.netdatalibrary.netData.data.refreshTokenData

import com.kompasid.netdatalibrary.base.interactor.ApiResults
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.interactor.Results

class RefreshTokenRepository(
    private val refreshTokenApiService: RefreshTokenApiService,
    private val refreshTokenDataSource: RefreshTokenDataSource
) {


    suspend fun postRefreshToken(): Results<Unit, NetworkError> {
        when (val result = refreshTokenApiService.postRefreshToken()) {
            is ApiResults.Success -> {
                result.data.let {
                    refreshTokenDataSource.save(
                        it.accessToken ?: "",
                        it.refreshToken ?: "",
                        it.isVerified ?: false,
                        it.deviceKeyId ?: "",
                        it.isSocial ?: false
                    )
                }

                return Results.Success(Unit)
            }
            // Jika terjadi error
            is ApiResults.Error -> {
                return Results.Error(result.error)
            }
        }
    }
}