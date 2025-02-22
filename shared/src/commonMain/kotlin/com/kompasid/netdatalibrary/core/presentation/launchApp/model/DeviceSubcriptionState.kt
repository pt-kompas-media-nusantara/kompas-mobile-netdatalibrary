package com.kompasid.netdatalibrary.core.presentation.launchApp.model

data class DeviceSubcriptionState(
    val originalTransactionId: List<String> = emptyList(),
    val transactionId: List<String> = emptyList(),
    val historyTransaction: List<String> = emptyList(),
)