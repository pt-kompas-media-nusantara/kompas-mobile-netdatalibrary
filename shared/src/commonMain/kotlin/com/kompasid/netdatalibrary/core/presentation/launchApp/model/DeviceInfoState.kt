package com.kompasid.netdatalibrary.core.presentation.launchApp.model

import kotlinx.serialization.Serializable

@Serializable
data class DeviceInfoState(
    val uiDeviceSystemName: String, // iOS | Synonym for model. Prior to iOS 16, user-assigned device name (e.g. @"My iPhone").
    val uiDeviceName: String, // John's iPhone
    val uiDeviceModel: String, // iPhone
    val uiDeviceSeries: String, // iPhone 6 Plus
    val deviceType: DeviceType, // smartphone, tablet, phablet, desktop
    val osVersion: String, // 17.4
    val currentAppVersionKompasId: String, // 3.50.0
)
