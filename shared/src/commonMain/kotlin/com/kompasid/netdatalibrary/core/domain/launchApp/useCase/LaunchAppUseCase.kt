package com.kompasid.netdatalibrary.core.domain.launchApp.useCase

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppModel
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class LaunchAppUseCase(
    private val settingsUseCase: SettingsUseCase
) {
    suspend fun execute(data: LaunchAppModel) = coroutineScope {
        listOf(
            async { settingsUseCase.save(KeySettingsType.FLAVORS, data.flavors) },
            async { settingsUseCase.save(KeySettingsType.ORIGINAL_TRANSACTION_ID, data.originalTransactionId) },
            async { settingsUseCase.save(KeySettingsType.TRANSACTION_ID, data.transactionId) },
            async { settingsUseCase.save(KeySettingsType.DEVICE_TYPE, data.deviceType) },
            async { settingsUseCase.save(KeySettingsType.OS_VERSION, data.osVersion) },
            async { settingsUseCase.save(KeySettingsType.CURRENT_VERSION_APP, data.currentVersionApp) },
            async { settingsUseCase.save(KeySettingsType.NEW_VERSION_APP, data.newVersionApp) },
            async { settingsUseCase.save(KeySettingsType.HISTORY_TRANSACTION, data.historyTransaction) },
            async { settingsUseCase.save(KeySettingsType.IS_DEBUG, data.isDebug) }
        ).awaitAll()
    }
}