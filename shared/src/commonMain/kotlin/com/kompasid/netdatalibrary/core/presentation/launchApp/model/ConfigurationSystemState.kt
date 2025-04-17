package com.kompasid.netdatalibrary.core.presentation.launchApp.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ConfigurationSystemState(
    val flavors: String = "",
    val isDebug: Boolean = false,
    val isLogActived: Boolean = false,
)