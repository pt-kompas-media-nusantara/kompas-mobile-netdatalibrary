package com.kompasid.netdatalibrary.netData.data.logoutData

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource


class LogoutDatasource(
    private val settingsDataSource: SettingsDataSource
) {
    fun logout() {
        settingsDataSource.remove(KeySettingsType.ACCESS_TOKEN)
        settingsDataSource.remove(KeySettingsType.REFRESH_TOKEN)
        settingsDataSource.remove(KeySettingsType.IS_VERIFIED)
        settingsDataSource.remove(KeySettingsType.DEVICE_KEY_ID)
        settingsDataSource.remove(KeySettingsType.IS_SOCIAL)
    }
}