package com.kompasid.netdatalibrary.core.data.logout.dataSource

import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource


class LogoutDataSource(
    private val settingsDataSource: SettingsDataSource
) {
    fun logout() {
        settingsDataSource.removeAll()
    }
}