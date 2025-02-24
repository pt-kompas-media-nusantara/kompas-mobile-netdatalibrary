package com.kompasid.netdatalibrary.core.domain.token.usecase

import com.kompasid.netdatalibrary.base.DecodeJWT
import com.kompasid.netdatalibrary.core.data.loginGuest.repository.LoginGuestRepository
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.core.data.refreshToken.repository.RefreshTokenRepository
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

class TokenUseCase(
    private val settingsHelper: SettingsHelper,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val loginGuestRepository: LoginGuestRepository,
    private val jwt: DecodeJWT,
) {

    suspend fun getValidToken(): Results<String, NetworkError> {
        var accessToken = loadAccessToken()

        if (accessToken.isEmpty()) {
            val loginResult = handleLoginGuest()
            when (loginResult) {
                is Results.Error -> {
                    Results.Error(loginResult.error)
                }
                is Results.Success -> {
                    return Results.Success(accessToken)
                }
            }
        }

        // Cek apakah token sudah kedaluwarsa
        if (jwt.isTokenExpired(accessToken)) {
            val refreshResult = handleRefreshToken()
            when (refreshResult) {
                is Results.Error -> {
                    Results.Error(refreshResult.error)
                }
                is Results.Success -> {
                    return Results.Success(accessToken)
                }
            }
        }

        // Kembalikan token yang valid
        return Results.Success(accessToken)
    }

    private suspend fun handleLoginGuest(): Results<String, NetworkError> {
        return when (val result = loginGuestRepository.postLoginGuest()) {
            is Results.Error -> {
                Results.Error(result.error)
            }
            is Results.Success -> Results.Success(result.data)
        }
    }

    private suspend fun handleRefreshToken(): Results<String, NetworkError> {
        return when (val result = refreshTokenRepository.postRefreshToken()) {
            is Results.Error -> {
                Results.Error(result.error)
            }

            is Results.Success -> Results.Success(result.data)
        }
    }

    private suspend fun loadAccessToken(): String {
        return settingsHelper.load(KeySettingsType.ACCESS_TOKEN)
    }


}
