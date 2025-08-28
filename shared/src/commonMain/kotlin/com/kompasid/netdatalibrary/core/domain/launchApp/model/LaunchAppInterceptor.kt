package com.kompasid.netdatalibrary.core.domain.launchApp.model

import com.kompasid.netdatalibrary.core.presentation.launchApp.model.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import kotlinx.serialization.Serializable

@Serializable
data class LaunchAppInterceptor(
    val deviceInfoState: DeviceInfoState,
    val deviceSubcriptionState: DeviceSubcriptionState,
    val configurationSystemState: ConfigurationSystemState,
)

