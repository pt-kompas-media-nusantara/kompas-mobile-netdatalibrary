package com.kompasid.netdatalibrary.core.presentation.launchApp.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class DeviceInfoState(
    val device: String = "",
    val deviceType: DeviceType = DeviceType.SMARTPHONE,
    val docReferrer: String = "",
    val osVersion: String = "",
    val currentVersionApp: String = "",
    val newVersionApp: String = "",
){
    fun toJson(): String {
        return Json.encodeToString(
            serializer(), DeviceInfoState(
                device, deviceType, docReferrer, osVersion, currentVersionApp, newVersionApp
            )
        )
    }

    companion object {
        fun fromJSON(data: String): DeviceInfoState {
            return Json.decodeFromString(data)
        }
    }
}

