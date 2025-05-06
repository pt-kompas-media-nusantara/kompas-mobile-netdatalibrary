package com.kompasid.netdatalibrary.core.data.loginGuest.dataSource

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

class LoginGuestDataSource(
    private val settingsHelper: SettingsHelper
) {
    suspend fun save(accessToken: String, refreshToken: String) {
        settingsHelper.save(KeySettingsType.ACCESS_TOKEN, accessToken)
        settingsHelper.save(KeySettingsType.REFRESH_TOKEN, refreshToken)
    }
}
