package com.kompasid.netdatalibrary.core.presentation.launchApp.model

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class DeviceInfoState(
    val uiDeviceSystemName: String = "",
    val uiDeviceName: String = "",
    val uiDeviceModel: String = "",
    val uiDeviceSeries: String = "",
    val device: String = "",
    val deviceType: DeviceType = DeviceType.SMARTPHONE,
    val osVersion: String = "",
    val currentVersionApp: String = "",
)
