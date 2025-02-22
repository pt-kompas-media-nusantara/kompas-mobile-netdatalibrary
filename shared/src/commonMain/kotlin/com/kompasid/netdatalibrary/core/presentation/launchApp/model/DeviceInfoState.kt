package com.kompasid.netdatalibrary.core.presentation.launchApp.model

data class DeviceInfoState(
    val deviceType: String = "",
    val osVersion: String = "",
    val currentVersionApp: String = "",
    val newVersionApp: String = "",
)