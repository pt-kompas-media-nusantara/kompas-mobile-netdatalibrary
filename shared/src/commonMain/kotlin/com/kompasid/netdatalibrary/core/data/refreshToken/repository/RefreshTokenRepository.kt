package com.kompasid.netdatalibrary.core.data.refreshToken.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.refreshToken.dataSource.RefreshTokenDataSource
import com.kompasid.netdatalibrary.core.data.refreshToken.network.RefreshTokenApiService


class RefreshTokenRepository(
    private val refreshTokenApiService: RefreshTokenApiService,
    private val refreshTokenDataSource: RefreshTokenDataSource
) : IRefreshTokenRepository {

    override suspend fun postRefreshToken(): Results<String, NetworkError> {
        return when (val result = refreshTokenApiService.postRefreshToken()) {
            is ApiResults.Success -> {
                val data = result.data.data

                val accessToken = data?.accessToken.orEmpty()
                val refreshToken = data?.refreshToken.orEmpty()
                val isVerified = data?.isVerified ?: false
                val deviceKeyId = data?.deviceKeyId.orEmpty()

                refreshTokenDataSource.save(accessToken, refreshToken, isVerified, deviceKeyId)

                Results.Success(accessToken)
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }
}
