package com.kompasid.netdatalibrary.netData.domain.tokenDomain

import com.kompasid.netdatalibrary.base.DecodeJWT
import com.kompasid.netdatalibrary.netData.data.loginGuestData.LoginGuestRepository
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.interactor.Results
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.netData.data.refreshTokenData.RefreshTokenRepository

class TokenUseCase(
    private val settingsDataSource: SettingsDataSource,
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
                settingsDataSource.removeAll()
                Results.Error(result.error)
            }

            is Results.Success -> Results.Success(Unit)
        }
    }

    private suspend fun loadAccessToken(): String {
        return settingsDataSource.load(KeySettingsType.ACCESS_TOKEN, "")
    }

    private suspend fun handleError(error: NetworkError): Results.Error<NetworkError> {
        settingsDataSource.removeAll()
        return Results.Error(error)
    }

}

