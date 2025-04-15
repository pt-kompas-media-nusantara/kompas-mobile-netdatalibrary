package com.kompasid.netdatalibrary.core.presentation.launchApp.stateState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.builtins.serializer


class LaunchAppResultState(
    private val settingsHelper: SettingsHelper,
) : BaseVM() {

    val deviceInfoState: StateFlow<DeviceInfoState> =
        combine(
            settingsHelper.load(KeySettingsType.DEVICE_TYPE, "").map { it ?: "" },
            settingsHelper.load(KeySettingsType.OS_VERSION, "").map { it ?: "" },
            settingsHelper.load(KeySettingsType.CURRENT_VERSION_APP, "").map { it ?: "" },
            settingsHelper.load(KeySettingsType.NEW_VERSION_APP, "").map { it ?: "" },
        ) { deviceType, osVersion, currentVersionApp, newVersionApp ->
            DeviceInfoState(
                deviceType = deviceType,
                osVersion = osVersion,
                currentVersionApp = currentVersionApp,
                newVersionApp = newVersionApp,
            )
        }
            .distinctUntilChanged()
            .debounce(300)
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
                DeviceInfoState()
            )

    val deviceSubscriptionState: StateFlow<DeviceSubcriptionState> =
        combine(
            settingsHelper.load(KeySettingsType.ORIGINAL_TRANSACTION_ID, emptyList<String>()),
            settingsHelper.load(KeySettingsType.TRANSACTION_ID, emptyList<String>()),
            settingsHelper.load(KeySettingsType.HISTORY_TRANSACTION, emptyList<String>()),
        ) { originalTransactionId, transactionId, historyTransaction ->
            DeviceSubcriptionState(
                originalTransactionId = originalTransactionId,
                transactionId = transactionId,
                historyTransaction = "historyTransaction",
            )
        }
            .distinctUntilChanged()
            .debounce(300)
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
                DeviceSubcriptionState()
            )


    val configurationSystemState: StateFlow<ConfigurationSystemState> =
        combine(
            settingsHelper.load(KeySettingsType.FLAVORS, "").map { it ?: "" },
            settingsHelper.load(KeySettingsType.FLAVORS, false).map { it ?: true }
        ) { flavors, isDebug ->
            ConfigurationSystemState(
                flavors = flavors,
                isDebug = isDebug
            )
        }
            .distinctUntilChanged()
            .debounce(300)
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
                ConfigurationSystemState()
            )
}

