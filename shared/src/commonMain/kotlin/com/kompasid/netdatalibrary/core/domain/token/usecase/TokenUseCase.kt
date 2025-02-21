package com.kompasid.netdatalibrary.core.domain.token.usecase

import com.kompasid.netdatalibrary.base.DecodeJWT
import com.kompasid.netdatalibrary.core.data.loginGuest.repository.LoginGuestRepository
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.core.data.refreshToken.repository.RefreshTokenRepository
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase

class TokenUseCase(
    private val settingsUseCase: SettingsUseCase,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val loginGuestRepository: LoginGuestRepository,
    private val jwt: DecodeJWT,
) {

    suspend fun getValidToken(): Results<String, NetworkError> {
        var accessToken = settingsUseCase.getString(KeySettingsType.ACCESS_TOKEN)

        if (accessToken.isEmpty()) {
            val loginResult = handleLoginGuest()
            if (loginResult is Results.Error) return loginResult
            accessToken = settingsUseCase.getString(KeySettingsType.ACCESS_TOKEN)
        }

        // Cek apakah token sudah kedaluwarsa
        if (jwt.isTokenExpired(accessToken)) {
            val refreshResult = handleRefreshToken()
            if (refreshResult is Results.Error) return refreshResult
            accessToken = settingsUseCase.getString(KeySettingsType.ACCESS_TOKEN)
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
                settingsUseCase.removeAll()
                Results.Error(result.error)
            }

            is Results.Success -> Results.Success(Unit)
        }
    }

    private suspend fun handleError(error: NetworkError): Results.Error<NetworkError> {
        settingsUseCase.removeAll()
        return Results.Error(error)
    }

}

