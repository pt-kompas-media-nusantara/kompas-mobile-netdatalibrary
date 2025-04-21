package com.kompasid.netdatalibrary.core.presentation.launchApp.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class DeviceSubcriptionState(
    val historyTransaction: String = "", // originalTransactionId & transactionId akan di ambil dari historyTransaction (convert sendiri)
)