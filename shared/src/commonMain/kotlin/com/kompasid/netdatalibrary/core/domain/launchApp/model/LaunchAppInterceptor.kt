package com.kompasid.netdatalibrary.core.domain.launchApp.model

import com.kompasid.netdatalibrary.core.presentation.launchApp.model.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState

data class LaunchAppInterceptor(
    val deviceInfoState: DeviceInfoState,
    val deviceSubcriptionState: DeviceSubcriptionState,
    val configurationSystemState: ConfigurationSystemState,
//    val flavors: String,
//    val originalTransactionId: String,
//    val transactionId: List<String>,
//    val deviceType: String,
//    val osVersion: String,
//    val currentVersionApp: String,
//    val newVersionApp: String,
//    val historyTransaction: List<String>,
//    val isDebug: Boolean,
)

