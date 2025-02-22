package com.kompasid.netdatalibrary.core.domain.launchApp.model

data class LaunchAppModel(
    val flavors: String,
    val originalTransactionId: List<String>,
    val transactionId: List<String>,
    val deviceType: String,
    val osVersion: String,
    val currentVersionApp: String,
    val newVersionApp: String,
    val historyTransaction: List<String>,
    val isDebug: Boolean,
)

