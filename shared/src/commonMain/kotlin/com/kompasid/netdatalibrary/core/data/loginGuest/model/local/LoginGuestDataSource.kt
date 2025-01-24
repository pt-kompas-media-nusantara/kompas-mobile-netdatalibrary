package com.kompasid.netdatalibrary.core.data.loginGuest.model.local

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource

class LoginGuestDataSource(
    private val settingsDataSource: SettingsDataSource
) {
    fun save(accessToken: String, refreshToken: String) {
        settingsDataSource.save(KeySettingsType.ACCESS_TOKEN, accessToken)
        settingsDataSource.save(KeySettingsType.REFRESH_TOKEN, refreshToken)
    }
}
