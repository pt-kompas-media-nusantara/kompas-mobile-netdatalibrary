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
            if (loginResult is Results.Error) return loginResult
            accessToken = loadAccessToken()
        }

        // Cek apakah token sudah kedaluwarsa
        if (jwt.isTokenExpired(accessToken)) {
            val refreshResult = handleRefreshToken()
            if (refreshResult is Results.Error) return refreshResult
            accessToken = loadAccessToken()
        }

        // Kembalikan token yang valid
        return Results.Success(accessToken)
    }

    private suspend fun handleLoginGuest(): Results<Unit, NetworkError> {
        return when (val result = loginGuestRepository.postLoginGuest()) {
            is Results.Error -> {
                handleError(result.error)
                Results.Error(result.error)
            }

            is Results.Success -> Results.Success(Unit)
        }
    }

    private suspend fun handleRefreshToken(): Results<Unit, NetworkError> {
        return when (val result = refreshTokenRepository.postRefreshToken()) {
            is Results.Error -> {
                handleError(result.error)
                settingsHelper.removeAll()
                Results.Error(result.error)
            }

            is Results.Success -> Results.Success(Unit)
        }
    }

    private suspend fun loadAccessToken(): String {
        return settingsHelper.getStringFlow(KeySettingsType.ACCESS_TOKEN).value ?: ""
    }

    private suspend fun handleError(error: NetworkError): Results.Error<NetworkError> {
        settingsHelper.removeAll()
        return Results.Error(error)
    }

}
