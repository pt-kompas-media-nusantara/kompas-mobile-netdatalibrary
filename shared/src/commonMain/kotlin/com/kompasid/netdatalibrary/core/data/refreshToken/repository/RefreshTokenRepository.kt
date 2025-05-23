package com.kompasid.netdatalibrary.core.data.refreshToken.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.refreshToken.model.local.RefreshTokenDataSource
import com.kompasid.netdatalibrary.core.data.refreshToken.network.RefreshTokenApiService

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