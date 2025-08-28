package com.kompasid.netdatalibrary.core.data.logout.dataSource

import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper


class LogoutDataSource(
    private val settingsHelper: SettingsHelper
) {
    suspend fun logout() {
        settingsHelper.removeAll()
    }
}