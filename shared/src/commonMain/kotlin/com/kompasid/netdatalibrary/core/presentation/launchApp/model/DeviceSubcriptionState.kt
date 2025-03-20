package com.kompasid.netdatalibrary.core.presentation.launchApp.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class DeviceSubcriptionState(
    val originalTransactionId: List<String> = emptyList(),
    val transactionId: List<String> = emptyList(),
    val historyTransaction: List<String> = emptyList(),
) {
    fun toJson(): String {
        return Json.encodeToString(
            serializer(), DeviceSubcriptionState(
                originalTransactionId, transactionId, historyTransaction
            )
        )
    }

    companion object {
        fun fromJSON(data: String): DeviceSubcriptionState {
            return Json.decodeFromString(data)
        }
    }
}