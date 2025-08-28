package com.kompasid.netdatalibrary.core.data.refreshToken.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results

interface IRefreshTokenRepository {
    suspend fun postRefreshToken(): Results<String, NetworkError>
}