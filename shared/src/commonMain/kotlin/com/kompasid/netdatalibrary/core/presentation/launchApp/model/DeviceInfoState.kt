package com.kompasid.netdatalibrary.core.presentation.launchApp.model

import kotlinx.serialization.Serializable

@Serializable
data class DeviceInfoState(
    val uiDeviceSystemName: String = "",
    val uiDeviceName: String = "",
    val uiDeviceModel: String = "",
    val uiDeviceSeries: String = "",
    val device: String = "",
    val deviceType: DeviceType = DeviceType.SMARTPHONE,
    val osVersion: String = "",
    val versionAppKompasId: String = "",
)
