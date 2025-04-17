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
        // "(\(UIDevice.current.systemName)) Native |  \(UIDevice.current.name) | \(UIDevice.current.model) | \(UIDevice.current.systemVersion) | Get From Library"
        val device: String = "(${data.deviceInfoState.uiDeviceSystemName}) Native |  ${data.deviceInfoState.uiDeviceName} | ${data.deviceInfoState.uiDeviceModel} | ${data.deviceInfoState.osVersion}| ${data.deviceInfoState.uiDeviceSeries}"
        listOf(
            // DeviceInfoState

            // hardcode sementara
            async { settingsHelper.save(KeySettingsType.IOS_UI_DEVICE_SYSTEM_NAME, data.deviceInfoState.uiDeviceSystemName) },
            async { settingsHelper.save(KeySettingsType.IOS_UI_DEVICE_NAME, data.deviceInfoState.uiDeviceName) },
            async { settingsHelper.save(KeySettingsType.IOS_UI_DEVICE_MODEL, data.deviceInfoState.uiDeviceModel) },
            async { settingsHelper.save(KeySettingsType.IOS_UI_DEVICE_SERIES, data.deviceInfoState.uiDeviceSeries) },

            async { settingsHelper.save(KeySettingsType.DEVICE, device) },

            async { settingsHelper.save(KeySettingsType.DEVICE_TYPE, data.deviceInfoState.deviceType.value) },
            async { settingsHelper.save(KeySettingsType.OS_VERSION, data.deviceInfoState.osVersion) },
            async { settingsHelper.save(KeySettingsType.VERSION_APP_KOMPAS_ID, data.deviceInfoState.currentVersionApp) },

            // DeviceSubcriptionState
            async { settingsHelper.save(KeySettingsType.ORIGINAL_TRANSACTION_ID, data.deviceSubcriptionState.originalTransactionId) },
            async { settingsHelper.save(KeySettingsType.TRANSACTION_ID, data.deviceSubcriptionState.transactionId) },
            async { settingsHelper.save(KeySettingsType.HISTORY_TRANSACTION, data.deviceSubcriptionState.historyTransaction) },

            // ConfigurationSystemState
            async { settingsHelper.save(KeySettingsType.FLAVORS, data.configurationSystemState.flavors) },
            async { settingsHelper.save(KeySettingsType.IS_DEBUG, data.configurationSystemState.isDebug) },
            async { settingsHelper.save(KeySettingsType.IS_LOG_ACTIVED, data.configurationSystemState.isLogActived) },
        ).awaitAll()
    }
}
