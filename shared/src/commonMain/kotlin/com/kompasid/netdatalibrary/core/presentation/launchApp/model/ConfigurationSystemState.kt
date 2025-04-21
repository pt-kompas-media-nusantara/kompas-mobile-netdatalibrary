package com.kompasid.netdatalibrary.core.presentation.launchApp.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ConfigurationSystemState(
    val flavors: String = "", // DEBUG, ALLCLOUD, DKID_ID, DKID_CLOUD, DQA_ID, DQA_CLOUD, RQA_ID, RKID_ID
    val isDebug: Boolean = false, // true or false
    val isLogActived: Boolean = false, // true or false
)