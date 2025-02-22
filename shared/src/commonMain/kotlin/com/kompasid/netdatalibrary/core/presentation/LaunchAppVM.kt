package com.kompasid.netdatalibrary.core.presentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppModel
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.core.presentation.personalInfo.deviceInfo.state.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.personalInfo.deviceInfo.state.DeviceInfoResultState
import com.kompasid.netdatalibrary.core.presentation.personalInfo.deviceInfo.state.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.personalInfo.deviceInfo.state.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.date.RelativeTimeFormatter
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.*

class LaunchAppVM(
    private val launchAppUseCase: LaunchAppUseCase,
    private val deviceInfoResultState: DeviceInfoResultState,
    private val settingsHelper: SettingsHelper
) : BaseVM() {

    val deviceInfoState: StateFlow<DeviceInfoState> =
        combine(
            settingsHelper.getStringFlow(KeySettingsType.DEVICE_TYPE).map { it ?: "" },
            settingsHelper.getStringFlow(KeySettingsType.OS_VERSION).map { it ?: "" },
            settingsHelper.getStringFlow(KeySettingsType.CURRENT_VERSION_APP).map { it ?: "" },
            settingsHelper.getStringFlow(KeySettingsType.NEW_VERSION_APP).map { it ?: "" },
        ) { deviceType, osVersion, currentVersionApp, newVersionApp ->
            DeviceInfoState(
                deviceType = deviceType,
                osVersion = osVersion,
                currentVersionApp = currentVersionApp,
                newVersionApp = newVersionApp,
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), DeviceInfoState())

    val deviceSubscriptionState: StateFlow<DeviceSubcriptionState> =
        combine(
            settingsHelper.getStringFlow(KeySettingsType.ORIGINAL_TRANSACTION_ID).map { it ?: "" },
            settingsHelper.getStringFlow(KeySettingsType.TRANSACTION_ID).map { it ?: "" },
            settingsHelper.getStringFlow(KeySettingsType.HISTORY_TRANSACTION).map { it ?: "" },
        ) { originalTransactionId, transactionId, historyTransaction ->
            DeviceSubcriptionState(
                originalTransactionId = originalTransactionId,
                transactionId = transactionId,
                historyTransaction = historyTransaction,
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), DeviceSubcriptionState())

    val configurationSystemState: StateFlow<ConfigurationSystemState> =
        combine(
            settingsHelper.getStringFlow(KeySettingsType.FLAVORS).map { it ?: "" },
            settingsHelper.getBooleanFlow(KeySettingsType.IS_DEBUG).map { it ?: true }
        ) { flavors, isDebug ->
            ConfigurationSystemState(
                flavors = flavors,
                isDebug = isDebug
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), ConfigurationSystemState())



    val originalTransactionId: StateFlow<String?> =
        settingsHelper.getStringFlow(KeySettingsType.ORIGINAL_TRANSACTION_ID).map { it ?: "" }
            .stateIn(scope, SharingStarted.WhileSubscribed(), "")


    suspend fun execute() {
        val data = LaunchAppModel(
            "flavors ${RelativeTimeFormatter().getCurrentTime()}",
            "originalTransactionId ${RelativeTimeFormatter().getCurrentTime()}",
            "transactionId ${RelativeTimeFormatter().getCurrentTime()}",
            "deviceType ${RelativeTimeFormatter().getCurrentTime()}",
            "osVersion ${RelativeTimeFormatter().getCurrentTime()}",
            "currentVersionApp ${RelativeTimeFormatter().getCurrentTime()}",
            "newVersionApp ${RelativeTimeFormatter().getCurrentTime()}",
            "historyTransaction ${RelativeTimeFormatter().getCurrentTime()}",
            true,
        )

        launchAppUseCase.execute(data)
    }


    fun load() {
//        this.settingsUseCase.getString(KeySettingsType.ORIGINAL_TRANSACTION_ID).toString()
    }
}


//val deviceInfoState: StateFlow<DeviceInfoState> =
//    combine(
//        settingsHelper.getStringFlow(KeySettingsType.ORIGINAL_TRANSACTION_ID).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.TRANSACTION_ID).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.DEVICE_TYPE).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.OS_VERSION).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.CURRENT_VERSION_APP).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.NEW_VERSION_APP).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.HISTORY_TRANSACTION).map { it ?: "" },
//        settingsHelper.getBooleanFlow(KeySettingsType.IS_DEBUG).map { it ?: true }
//    ) { originalTransactionId: String, transactionId: String, deviceType: String, osVersion: String,
//        currentVersionApp: String, newVersionApp: String, historyTransaction: String, isDebug: Boolean ->
//
//        DeviceInfoState(
//            originalTransactionId = originalTransactionId,
//            transactionId = transactionId,
//            deviceType = deviceType,
//            osVersion = osVersion,
//            currentVersionApp = currentVersionApp,
//            newVersionApp = newVersionApp,
//            historyTransaction = historyTransaction,
//            isDebug = isDebug
//        )
//    }.stateIn(scope, SharingStarted.WhileSubscribed(), DeviceInfoState())
