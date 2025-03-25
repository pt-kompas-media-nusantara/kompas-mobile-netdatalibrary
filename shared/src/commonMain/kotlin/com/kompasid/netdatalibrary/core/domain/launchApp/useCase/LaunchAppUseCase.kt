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
            // DeviceInfoState
            async { settingsHelper.save(KeySettingsType.DEVICE, data.deviceInfoState.device) },
            async { settingsHelper.save(KeySettingsType.DEVICE_TYPE, data.deviceInfoState.deviceType) },
            async { settingsHelper.save(KeySettingsType.DOC_REFERRER, data.deviceInfoState.deviceType) },
            async { settingsHelper.save(KeySettingsType.OS_VERSION, data.deviceInfoState.osVersion) },
            async { settingsHelper.save(KeySettingsType.CURRENT_VERSION_APP, data.deviceInfoState.currentVersionApp) },
            async { settingsHelper.save(KeySettingsType.NEW_VERSION_APP, data.deviceInfoState.newVersionApp) },

            // DeviceSubcriptionState
            async { settingsHelper.save(KeySettingsType.ORIGINAL_TRANSACTION_ID, data.deviceSubcriptionState.originalTransactionId) },
            async { settingsHelper.save(KeySettingsType.TRANSACTION_ID, data.deviceSubcriptionState.transactionId) },
            async { settingsHelper.save(KeySettingsType.HISTORY_TRANSACTION, data.deviceSubcriptionState.historyTransaction) },

            // ConfigurationSystemState
            async { settingsHelper.save(KeySettingsType.FLAVORS, data.configurationSystemState.flavors) },
            async { settingsHelper.save(KeySettingsType.IS_DEBUG, data.configurationSystemState.isDebug) }
        ).awaitAll()
    }
}