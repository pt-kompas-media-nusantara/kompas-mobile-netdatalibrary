package com.kompasid.netdatalibrary.netData.presentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.netData.data.loginEmailData.dto.LoginEmailRequest
import com.kompasid.netdatalibrary.netData.domain.SettingsDomain.SettingsUseCase
import com.kompasid.netdatalibrary.netData.domain.authDomain.AuthUseCase
import com.kompasid.netdatalibrary.utilities.Constants
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthVM(
    private val authUseCase: AuthUseCase,
    private val settingsUseCase: SettingsUseCase,
    private val settingsDataSource: SettingsDataSource,
) : BaseVM() {

    val accessToken: StateFlow<String?> =
        settingsDataSource.getStringFlow(KeySettingsType.ACCESS_TOKEN).map { it ?: "" }
            .stateIn(scope, SharingStarted.WhileSubscribed(), "")

    fun cetakAllSettings() {
        scope.launch {
            settingsUseCase.loadAllSettings()
        }
    }

    fun cetakAccessSettings() {
        scope.launch {
            settingsUseCase.loadAccessToken()
        }
    }


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