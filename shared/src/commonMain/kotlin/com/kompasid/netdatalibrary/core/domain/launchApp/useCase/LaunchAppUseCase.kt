package com.kompasid.netdatalibrary.core.domain.launchApp.useCase

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppInterceptor
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helpers.ValidateOSVersion
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class LaunchAppUseCase(
    private val settingsHelper: SettingsHelper,
) {

    /// put here : didFinishLaunchingWithOptions
    suspend fun execute(data: LaunchAppInterceptor) = coroutineScope {
        saveDeviceInfo(data.deviceInfoState)
        saveSubscriptionInfo(data.deviceSubcriptionState)
        saveConfigurationInfo(data.configurationSystemState)

        checkAndUpdateAppVersion(data.deviceInfoState.currentAppVersionKompasId)
    }

    private suspend fun saveDeviceInfo(device: DeviceInfoState) {
        coroutineScope {
            val deviceDescription = "(${device.uiDeviceSystemName}) Native | " +
                    "${device.uiDeviceName} | ${device.uiDeviceModel} | " +
                    "${device.osVersion} | ${device.uiDeviceSeries}"
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.IOS_UI_DEVICE_SYSTEM_NAME, device.uiDeviceSystemName),
                settingsHelper.saveAsync(this, KeySettingsType.IOS_UI_DEVICE_NAME, device.uiDeviceName),
                settingsHelper.saveAsync(this, KeySettingsType.IOS_UI_DEVICE_MODEL, device.uiDeviceModel),
                settingsHelper.saveAsync(this, KeySettingsType.IOS_UI_DEVICE_SERIES, device.uiDeviceSeries),
                settingsHelper.saveAsync(this, KeySettingsType.DEVICE, deviceDescription),
                settingsHelper.saveAsync(this, KeySettingsType.DEVICE_TYPE, device.deviceType.value),
                settingsHelper.saveAsync(this, KeySettingsType.OS_VERSION, device.osVersion),
            ).awaitAll()
        }
    }


    private suspend fun saveSubscriptionInfo(subscription: DeviceSubcriptionState) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.HISTORY_TRANSACTION, subscription.historyTransaction)
            ).awaitAll()
        }
    }

    private suspend fun saveConfigurationInfo(config: ConfigurationSystemState) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.FLAVORS, config.flavors),
                settingsHelper.saveAsync(this, KeySettingsType.IS_DEBUG, config.isDebug),
                settingsHelper.saveAsync(this, KeySettingsType.IS_LOG_ACTIVED, config.isLogActived)
            ).awaitAll()
        }
    }

    private suspend fun checkAndUpdateAppVersion(currentVersionString: String) {
        val currentVersions: List<String> = settingsHelper.get(
            KeySettingsType.APP_VERSIONS_KOMPAS_ID, emptyList()
        )

        if (currentVersions.isEmpty()) {
            settingsHelper.save(KeySettingsType.STATE_INSTALL, 1)
            settingsHelper.save(KeySettingsType.APP_VERSIONS_KOMPAS_ID, listOf(currentVersionString))
        } else if (!currentVersions.contains(currentVersionString)) {
            settingsHelper.save(KeySettingsType.STATE_INSTALL, 2)
            val updatedVersions = currentVersions + currentVersionString
            settingsHelper.save(KeySettingsType.APP_VERSIONS_KOMPAS_ID, updatedVersions)
        }
    }



}
