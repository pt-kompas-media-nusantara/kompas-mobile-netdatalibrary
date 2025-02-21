package com.kompasid.netdatalibrary.core.data.logout.dataSource

import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper


class LogoutDataSource(
    private val settingsUseCase: SettingsUseCase
) {
    suspend fun logout() {
        settingsUseCase.removeAll()
    }
}