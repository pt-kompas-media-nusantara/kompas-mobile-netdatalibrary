package com.kompasid.netdatalibrary.core.data.refreshToken.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.refreshToken.dto.response.RefreshTokenResponse

interface IRefreshTokenApiService {
    suspend fun postRefreshToken(): ApiResults<RefreshTokenResponse, NetworkError>
}