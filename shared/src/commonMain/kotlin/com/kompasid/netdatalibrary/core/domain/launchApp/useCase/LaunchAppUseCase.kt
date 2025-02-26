package com.kompasid.netdatalibrary.core.domain.launchApp.useCase

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppInterceptor
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class LaunchAppUseCase(
    private val settingsHelper: SettingsHelper
) {
    suspend fun execute(data: LaunchAppInterceptor) = coroutineScope {
        listOf(
            async { settingsHelper.save(KeySettingsType.FLAVORS, data.configurationSystemState.flavors) },
//            async { settingsHelper.save(KeySettingsType.ORIGINAL_TRANSACTION_ID, data.originalTransactionId) },
//            async { settingsHelper.save(KeySettingsType.TRANSACTION_ID, data.transactionId) }, // nurirppan__ : ini masih blm di save
            async { settingsHelper.save(KeySettingsType.DEVICE_TYPE, data.deviceInfoState.deviceType) },
            async { settingsHelper.save(KeySettingsType.OS_VERSION, data.deviceInfoState.osVersion) },
            async { settingsHelper.save(KeySettingsType.CURRENT_VERSION_APP, data.deviceInfoState.currentVersionApp) },
            async { settingsHelper.save(KeySettingsType.NEW_VERSION_APP, data.deviceInfoState.newVersionApp) },
//            async { settingsHelper.save(KeySettingsType.HISTORY_TRANSACTION, data.historyTransaction) },
            async { settingsHelper.save(KeySettingsType.IS_DEBUG, data.configurationSystemState.isDebug) }
        ).awaitAll()
    }
}