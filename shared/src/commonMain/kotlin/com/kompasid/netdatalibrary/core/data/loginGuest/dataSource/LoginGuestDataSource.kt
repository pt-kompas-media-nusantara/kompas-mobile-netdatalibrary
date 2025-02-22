package com.kompasid.netdatalibrary.core.data.loginGuest.dataSource

import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

class LoginGuestDataSource(
    private val settingsUseCase: SettingsUseCase
) {
    suspend fun save(accessToken: String, refreshToken: String) {
//        settingsUseCase.save(KeySettingsType.ACCESS_TOKEN, accessToken)
//        settingsUseCase.save(KeySettingsType.REFRESH_TOKEN, refreshToken)
    }
}
