package com.kompasid.netdatalibrary.core.domain.launchApp.useCase

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppModel
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase

class LaunchAppUseCase(
    private val settingsUseCase: SettingsUseCase
) {
    suspend fun execute(data: LaunchAppModel) {
        settingsUseCase.save(KeySettingsType.FLAVORS, data.flavors)
        settingsUseCase.save(KeySettingsType.ORIGINAL_TRANSACTION_ID, data.originalTransactionId)
        settingsUseCase.save(KeySettingsType.TRANSACTION_ID, data.transactionId)
        settingsUseCase.save(KeySettingsType.DEVICE_TYPE, data.deviceType)
        settingsUseCase.save(KeySettingsType.OS_VERSION, data.osVersion)
        settingsUseCase.save(KeySettingsType.CURRENT_VERSION_APP, data.currentVersionApp)
        settingsUseCase.save(KeySettingsType.NEW_VERSION_APP, data.newVersionApp)
        settingsUseCase.save(KeySettingsType.HISTORY_TRANSACTION, data.historyTransaction)
        settingsUseCase.save(KeySettingsType.IS_DEBUG, data.isDebug)
    }
}