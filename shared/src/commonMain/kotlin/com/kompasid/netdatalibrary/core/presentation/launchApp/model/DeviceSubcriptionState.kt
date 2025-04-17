package com.kompasid.netdatalibrary.core.presentation.launchApp.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class DeviceSubcriptionState(
    val originalTransactionId: List<String> = emptyList(),
    val transactionId: List<String> = emptyList(),
    val historyTransaction: String = "",
)