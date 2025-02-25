package com.kompasid.netdatalibrary.core.data.loginEmail.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.interceptor.LoginInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LoginResultState(
    private val settingsHelper: SettingsHelper,
) : BaseVM() {

    val accessToken: StateFlow<String> = settingsHelper.load(KeySettingsType.ACCESS_TOKEN, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val refreshToken: StateFlow<String> = settingsHelper.load(KeySettingsType.REFRESH_TOKEN, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val data: StateFlow<LoginInterceptor> = combine(
        accessToken, refreshToken
    ) { accessToken, refreshToken ->
        LoginInterceptor(accessToken, refreshToken)
    }
        .distinctUntilChanged()
        .debounce(300)
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), LoginInterceptor())
}
