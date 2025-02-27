package com.kompasid.netdatalibrary.helper.persistentStorage.example.finalNoArgModuleSettings

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.date.RelativeTimeFormatter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FinalNoArgModuleSettingsVM(
    private val settingsHelper: SettingsHelper
) : BaseVM() {

    val originalTransactionId: StateFlow<List<String>> =
        settingsHelper.load(KeySettingsType.ORIGINAL_TRANSACTION_ID, emptyList<String>())
            .stateIn(scope, SharingStarted.Lazily, emptyList())

    val transactionId: StateFlow<List<String>> =
        settingsHelper.load(KeySettingsType.TRANSACTION_ID, emptyList<String>())
            .stateIn(scope, SharingStarted.Lazily, emptyList<String>())

    val historyTransaction: StateFlow<List<String>> =
        settingsHelper.load(KeySettingsType.HISTORY_TRANSACTION, emptyList<String>())
            .stateIn(scope, SharingStarted.Lazily, emptyList())

    val trytry: StateFlow<DeviceSubcriptionState> =
        settingsHelper.load(
            KeySettingsType.TRYTRY,
            DeviceSubcriptionState(),
            DeviceSubcriptionState.serializer()
        ).stateIn(scope, SharingStarted.Lazily, DeviceSubcriptionState())

    fun saveList() {
        scope.launch {
            settingsHelper.save(
                KeySettingsType.ORIGINAL_TRANSACTION_ID, listOf(
                    "ORIGINAL_TRANSACTION_ID 1 - ${RelativeTimeFormatter().getCurrentTime()}",
                    "ORIGINAL_TRANSACTION_ID 2 - ${RelativeTimeFormatter().getCurrentTime()}",
                )
            )

            settingsHelper.save(
                KeySettingsType.TRANSACTION_ID, listOf(
                    "TRANSACTION_ID 1 - ${RelativeTimeFormatter().getCurrentTime()}",
                    "TRANSACTION_ID 2 - ${RelativeTimeFormatter().getCurrentTime()}",
                )
            )

            settingsHelper.save(
                KeySettingsType.HISTORY_TRANSACTION, listOf(
                    "HISTORY_TRANSACTION 1 - ${RelativeTimeFormatter().getCurrentTime()}",
                    "HISTORY_TRANSACTION 2 - ${RelativeTimeFormatter().getCurrentTime()}",
                )
            )

            val originalTransactionId: List<String> =
                settingsHelper.get(KeySettingsType.ORIGINAL_TRANSACTION_ID, emptyList())
            val transactionId: List<String> =
                settingsHelper.get(KeySettingsType.TRANSACTION_ID, emptyList())
            val historyTransaction: List<String> =
                settingsHelper.get(KeySettingsType.HISTORY_TRANSACTION, emptyList())
        }
    }

    fun saveModel() {
        scope.launch {
            val data = DeviceSubcriptionState(
                originalTransactionId = listOf(
                    "originalTransactionId 1 - ${RelativeTimeFormatter().getCurrentTime()}",
                    "originalTransactionId 2 - ${RelativeTimeFormatter().getCurrentTime()}",
                ),
                transactionId = listOf(
                    "transactionId 1 - ${RelativeTimeFormatter().getCurrentTime()}",
                    "transactionId 2 - ${RelativeTimeFormatter().getCurrentTime()}",
                ),
                historyTransaction = listOf(
                    "historyTransaction 1 - ${RelativeTimeFormatter().getCurrentTime()}",
                    "historyTransaction 2 - ${RelativeTimeFormatter().getCurrentTime()}",
                ),
            )

            settingsHelper.save(
                KeySettingsType.TRYTRY,
                data.toJson()
            )

            delay(3000)
            val result = DeviceSubcriptionState.fromJSON(settingsHelper.get(
                KeySettingsType.TRYTRY,
                ""
            ))

            Logger.info { result.toString() }
        }
    }
}