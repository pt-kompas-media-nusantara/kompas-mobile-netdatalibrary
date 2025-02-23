package com.kompasid.netdatalibrary.core.data.loginEmail.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.interceptor.LoginInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LoginResultState(
    private val settingsHelper: SettingsHelper,
) : BaseVM() {
    val loginInterceptor: StateFlow<LoginInterceptor> =
        combine(
            settingsHelper.getStringFlow(KeySettingsType.ACCESS_TOKEN).map { it ?: "" },
            settingsHelper.getStringFlow(KeySettingsType.REFRESH_TOKEN).map { it ?: "" },
        ) { accessToken, refreshToken ->
            LoginInterceptor(
                accessToken = accessToken,
                refreshToken = refreshToken,
            )
        }
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
                LoginInterceptor()
            )
}
