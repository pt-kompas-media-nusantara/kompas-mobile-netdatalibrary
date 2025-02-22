package com.kompasid.netdatalibrary.core.presentation.launchApp.state

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class LaunchAppResultState(
    private val settingsHelper: SettingsHelper,
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
        }
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.WhileSubscribed(replayExpirationMillis = 5000), DeviceInfoState())

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
        }
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.WhileSubscribed(replayExpirationMillis = 5000), DeviceSubcriptionState())

    val configurationSystemState: StateFlow<ConfigurationSystemState> =
        combine(
            settingsHelper.getStringFlow(KeySettingsType.FLAVORS).map { it ?: "" },
            settingsHelper.getBooleanFlow(KeySettingsType.IS_DEBUG).map { it ?: true }
        ) { flavors, isDebug ->
            ConfigurationSystemState(
                flavors = flavors,
                isDebug = isDebug
            )
        }
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.WhileSubscribed(replayExpirationMillis = 5000), ConfigurationSystemState())
}

