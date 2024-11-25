package com.kompasid.netdatalibrary.netData.domain.tokenDomain

import JwtParser
import com.kompasid.netdatalibrary.netData.data.loginGuestData.LoginGuestRepository
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.interactor.Results
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.netData.data.refreshTokenData.RefreshTokenRepository
import kotlinx.datetime.Clock
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

class TokenUseCase(
    private val settingsDataSource: SettingsDataSource,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val loginGuestRepository: LoginGuestRepository,
) {
    companion object {
        private const val EXPIRATION_MARGIN_SECONDS = 20
    }

    suspend fun getValidToken(): Results<String, NetworkError> {
        var accessToken = loadAccessToken()

        if (accessToken.isEmpty()) {
            val loginResult = handleLoginGuest()
            if (loginResult is Results.Error) return loginResult
            accessToken = loadAccessToken()
        }

        // Cek apakah token sudah kedaluwarsa
        if (isTokenExpired(accessToken)) {
            val refreshResult = handleRefreshToken()
            if (refreshResult is Results.Error) return refreshResult
            accessToken = loadAccessToken()
        }

        // Kembalikan token yang valid
        return Results.Success(accessToken)
    }

    private fun isTokenExpired(token: String): Boolean {
        if (token.isEmpty()) return true

        val parser = JwtParser()
        val jsonObject = parser.parseToJsonObject(token)
        val exp = jsonObject?.get("exp")?.jsonPrimitive?.long ?: 0L

        // Tambahkan margin waktu (10 detik) untuk menghindari masalah delay
        val currentTime = Clock.System.now().epochSeconds
        return currentTime >= exp - EXPIRATION_MARGIN_SECONDS
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

