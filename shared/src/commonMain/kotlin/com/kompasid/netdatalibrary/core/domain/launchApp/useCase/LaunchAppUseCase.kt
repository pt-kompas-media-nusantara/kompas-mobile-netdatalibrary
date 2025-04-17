package com.kompasid.netdatalibrary.core.domain.launchApp.useCase

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppInterceptor
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helpers.ValidateOSVersion
import com.kompasid.netdatalibrary.utilities.SettingsSaver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class LaunchAppUseCase(
    private val settingsHelper: SettingsHelper,
    private val settingsSaver: SettingsSaver
) {
    suspend fun execute(data: LaunchAppInterceptor) = coroutineScope {
        saveDeviceInfo(data.deviceInfoState)
        saveSubscriptionInfo(data.deviceSubcriptionState)
        saveConfigurationInfo(data.configurationSystemState)

        checkAndUpdateAppVersion(data.deviceInfoState.versionAppKompasId)
    }

    private suspend fun saveDeviceInfo(device: DeviceInfoState) {
        coroutineScope {
            val deviceDescription = "(${device.uiDeviceSystemName}) Native | " +
                    "${device.uiDeviceName} | ${device.uiDeviceModel} | " +
                    "${device.osVersion} | ${device.uiDeviceSeries}"
            listOf(
                settingsSaver.saveAsync(this, KeySettingsType.IOS_UI_DEVICE_SYSTEM_NAME, device.uiDeviceSystemName),
                settingsSaver.saveAsync(this, KeySettingsType.IOS_UI_DEVICE_NAME, device.uiDeviceName),
                settingsSaver.saveAsync(this, KeySettingsType.IOS_UI_DEVICE_MODEL, device.uiDeviceModel),
                settingsSaver.saveAsync(this, KeySettingsType.IOS_UI_DEVICE_SERIES, device.uiDeviceSeries),
                settingsSaver.saveAsync(this, KeySettingsType.DEVICE, deviceDescription),
                settingsSaver.saveAsync(this, KeySettingsType.DEVICE_TYPE, device.deviceType.value),
                settingsSaver.saveAsync(this, KeySettingsType.OS_VERSION, device.osVersion),
                settingsSaver.saveAsync(this, KeySettingsType.APP_VERSION_KOMPAS_ID, device.versionAppKompasId)
            ).awaitAll()
        }
    }


    private suspend fun saveSubscriptionInfo(subscription: DeviceSubcriptionState) {
        coroutineScope {
            listOf(
                settingsSaver.saveAsync(this, KeySettingsType.ORIGINAL_TRANSACTION_ID, subscription.originalTransactionId),
                settingsSaver.saveAsync(this, KeySettingsType.TRANSACTION_ID, subscription.transactionId),
                settingsSaver.saveAsync(this, KeySettingsType.HISTORY_TRANSACTION, subscription.historyTransaction)
            ).awaitAll()
        }
    }

    private suspend fun saveConfigurationInfo(config: ConfigurationSystemState) {
        coroutineScope {
            listOf(
                settingsSaver.saveAsync(this, KeySettingsType.FLAVORS, config.flavors),
                settingsSaver.saveAsync(this, KeySettingsType.IS_DEBUG, config.isDebug),
                settingsSaver.saveAsync(this, KeySettingsType.IS_LOG_ACTIVED, config.isLogActived)
            ).awaitAll()
        }
    }

    private suspend fun checkAndUpdateAppVersion(currentVersionString: String) {
        val storedVersion = settingsHelper.get(KeySettingsType.APP_VERSION_KOMPAS_ID, "")
        val latestVersion = settingsHelper.get(KeySettingsType.APP_VERSION_KOMPAS_ID_LATEST, "")

        if (storedVersion.isEmpty()) {
            settingsHelper.save(KeySettingsType.STATE_INSTALL, 1)
            settingsHelper.save(KeySettingsType.APP_VERSION_KOMPAS_ID_LATEST, currentVersionString)
        } else {
            val currentVersion = ValidateOSVersion.parse(storedVersion)
            val latest = ValidateOSVersion.parse(latestVersion)

            if (currentVersion != latest) {
                settingsHelper.save(KeySettingsType.STATE_INSTALL, 2)
                settingsHelper.save(KeySettingsType.APP_VERSION_KOMPAS_ID, currentVersionString)
                settingsHelper.save(KeySettingsType.APP_VERSION_KOMPAS_ID_LATEST, currentVersionString)
            }
        }
    }


}
