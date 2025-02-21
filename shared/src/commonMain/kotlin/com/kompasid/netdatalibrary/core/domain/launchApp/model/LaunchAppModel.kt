package com.kompasid.netdatalibrary.core.domain.launchApp.model

data class LaunchAppModel(
    val flavors: String,
    val originalTransactionId: String,
    val transactionId: String,
    val deviceType: String,
    val osVersion: String,
    val currentVersionApp: String,
    val newVersionApp: String,
    val historyTransaction: String,
    val isDebug: Boolean,
)

