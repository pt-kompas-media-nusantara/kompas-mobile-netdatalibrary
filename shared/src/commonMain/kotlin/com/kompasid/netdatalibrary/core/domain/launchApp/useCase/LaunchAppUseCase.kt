package com.kompasid.netdatalibrary.core.domain.launchApp.useCase

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class LaunchAppUseCase(
    private val settingsHelper: SettingsHelper
) {
    suspend fun execute(data: LaunchAppModel) = coroutineScope {
        listOf(
            async { settingsHelper.save(KeySettingsType.FLAVORS, data.flavors) },
            async { settingsHelper.save(KeySettingsType.ORIGINAL_TRANSACTION_ID, data.originalTransactionId) },
            async { settingsHelper.save(KeySettingsType.TRANSACTION_ID, data.transactionId) },
            async { settingsHelper.save(KeySettingsType.DEVICE_TYPE, data.deviceType) },
            async { settingsHelper.save(KeySettingsType.OS_VERSION, data.osVersion) },
            async { settingsHelper.save(KeySettingsType.CURRENT_VERSION_APP, data.currentVersionApp) },
            async { settingsHelper.save(KeySettingsType.NEW_VERSION_APP, data.newVersionApp) },
            async { settingsHelper.save(KeySettingsType.HISTORY_TRANSACTION, data.historyTransaction) },
            async { settingsHelper.save(KeySettingsType.IS_DEBUG, data.isDebug) }
        ).awaitAll()
    }
}