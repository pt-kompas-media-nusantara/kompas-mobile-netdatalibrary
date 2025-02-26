package com.kompasid.netdatalibrary.core.presentation.launchApp.stateState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppInterceptor
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.date.RelativeTimeFormatter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LaunchAppVM(
    private val launchAppUseCase: LaunchAppUseCase,
    private val launchAppResultState: LaunchAppResultState,
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

    fun saveList() {
        scope.launch {
            settingsHelper.save(
                KeySettingsType.ORIGINAL_TRANSACTION_ID, listOf(
                    "ORIGINAL_TRANSACTION_ID 1 : ${RelativeTimeFormatter().getCurrentTime()}",
                    "ORIGINAL_TRANSACTION_ID 2 : ${RelativeTimeFormatter().getCurrentTime()}",
                )
            )

            settingsHelper.save(
                KeySettingsType.TRANSACTION_ID, listOf(
                    "TRANSACTION_ID 1 : ${RelativeTimeFormatter().getCurrentTime()}",
                    "TRANSACTION_ID 2 : ${RelativeTimeFormatter().getCurrentTime()}",
                )
            )

            settingsHelper.save(
                KeySettingsType.HISTORY_TRANSACTION, listOf(
                    "HISTORY_TRANSACTION 1 : ${RelativeTimeFormatter().getCurrentTime()}",
                    "HISTORY_TRANSACTION 2 : ${RelativeTimeFormatter().getCurrentTime()}",
                )
            )

            val originalTransactionId: List<String> =
                settingsHelper.get(KeySettingsType.ORIGINAL_TRANSACTION_ID, emptyList())
            val transactionId: List<String> =
                settingsHelper.get(KeySettingsType.TRANSACTION_ID, emptyList())
            val historyTransaction: List<String> =
                settingsHelper.get(KeySettingsType.HISTORY_TRANSACTION, emptyList())

            Logger.debug { originalTransactionId.toString() }
            Logger.debug { transactionId.toString() }
            Logger.debug { historyTransaction.toString() }

        }
    }


//            settingsHelper.save(
//                KeySettingsType.TRY_DEVICE_SUBCRIPTION_STATE,
//                DeviceSubcriptionState(
//                    originalTransactionId = listOf(
//                        "originalTransactionId 1 : ${RelativeTimeFormatter().getCurrentTime()}",
//                        "originalTransactionId 2 : ${RelativeTimeFormatter().getCurrentTime()}",
//                    ),
//                    transactionId = listOf(
//                        "transactionId 1 : ${RelativeTimeFormatter().getCurrentTime()}",
//                        "transactionId 2 : ${RelativeTimeFormatter().getCurrentTime()}",
//                    ),
//                    historyTransaction = listOf(
//                        "historyTransaction 1 : ${RelativeTimeFormatter().getCurrentTime()}",
//                        "historyTransaction 2 : ${RelativeTimeFormatter().getCurrentTime()}",
//                    ),
//                ),
//                serializer = DeviceSubcriptionState.serializer() // ✅ Tambahkan serializer
//            )

//    val deviceInfoState: StateFlow<DeviceInfoState> = launchAppResultState.deviceInfoState
//    val deviceSubscriptionState: StateFlow<DeviceSubcriptionState> =
//        launchAppResultState.deviceSubscriptionState
//    val configurationSystemState: StateFlow<ConfigurationSystemState> =
//        launchAppResultState.configurationSystemState


//    suspend fun execute() {
//        try {
//            val data = LaunchAppInterceptor(
//                deviceInfoState = DeviceInfoState(
//                    deviceType = "deviceType ${RelativeTimeFormatter().getCurrentTime()}",
//                    osVersion = "osVersion ${RelativeTimeFormatter().getCurrentTime()}",
//                    currentVersionApp = "currentVersionApp ${RelativeTimeFormatter().getCurrentTime()}",
//                    newVersionApp = "newVersionApp ${RelativeTimeFormatter().getCurrentTime()}"
//                ),
//                deviceSubcriptionState = DeviceSubcriptionState(
//                    originalTransactionId = listOf(
//                        "1 : ${RelativeTimeFormatter().getCurrentTime()}",
//                        "2 : ${RelativeTimeFormatter().getCurrentTime()}",
//                    ),
//                    transactionId = listOf(
//                        "1 : ${RelativeTimeFormatter().getCurrentTime()}",
//                        "2 : ${RelativeTimeFormatter().getCurrentTime()}",
//                    ),
//                    historyTransaction = listOf(
//                        "1 : ${RelativeTimeFormatter().getCurrentTime()}",
//                        "2 : ${RelativeTimeFormatter().getCurrentTime()}",
//                    ),
//                ),
//                configurationSystemState = ConfigurationSystemState(
//                    flavors = "flavors ${RelativeTimeFormatter().getCurrentTime()}",
//                    isDebug = false
//                )
//            )
//
//            launchAppUseCase.execute(data)
//        } catch (e: Exception) {
//            // Menangani error agar tidak crash
//            Logger.error { "Error executing: ${e.message}" }
//        }
//    }
}


