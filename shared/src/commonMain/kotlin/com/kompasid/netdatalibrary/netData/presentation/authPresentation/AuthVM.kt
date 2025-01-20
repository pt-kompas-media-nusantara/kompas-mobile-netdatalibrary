package com.kompasid.netdatalibrary.netData.presentation.authPresentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.DecodeJWT
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.netData.data.loginEmailData.dto.LoginEmailRequest
import com.kompasid.netdatalibrary.netData.domain.authDomain.AuthUseCase
import com.kompasid.netdatalibrary.utilities.Constants
import kotlinx.coroutines.launch

class AuthVM(
    private val authUseCase: AuthUseCase,
    private val settingsDataSource: SettingsDataSource,
) : BaseVM() {

    fun postLoginGuest() {
        scope.launch {
            val result = authUseCase.loginAnon()
            when (result) {
                is Results.Error -> {
                    Logger.debug { result.error.toString() }
                }

                is Results.Success -> {
                    val interceptor = result.data
                    Logger.debug { interceptor.toString() }
                }
            }
        }
    }

    fun postLoginByEmail() {
        scope.launch {
            authUseCase.loginByEmail(
                LoginEmailRequest(
                    "nur.irfan@kompas.com",
                    "Nurirppankompas@28",
                    "testKMP",
                    "testKMP",
                )
            )
        }
    }

    fun postLogout() {
        scope.launch {
            authUseCase.logout()
        }
    }

    fun decodeJWT() {
        val accessToken =
            settingsDataSource.save(KeySettingsType.ACCESS_TOKEN, Constants.HARDCODE_ACCESS_TOKEN)
        val refreshToken =
            settingsDataSource.save(KeySettingsType.REFRESH_TOKEN, Constants.HARDCODE_REFRESH_TOKEN)
//        val result = DecodeJWT().decodeJwt(accessToken.toString()).exp
//        Logger.debug { result.toString() }
    }
}