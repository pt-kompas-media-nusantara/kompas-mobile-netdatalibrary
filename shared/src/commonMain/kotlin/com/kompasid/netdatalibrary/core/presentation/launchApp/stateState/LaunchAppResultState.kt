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
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
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
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
                DeviceInfoState()
            )

    val deviceSubscriptionState: StateFlow<DeviceSubcriptionState> =
        combine(
            settingsHelper.getStringListFlow(KeySettingsType.ORIGINAL_TRANSACTION_ID),
            settingsHelper.getStringListFlow(KeySettingsType.TRANSACTION_ID),
            settingsHelper.getStringListFlow(KeySettingsType.HISTORY_TRANSACTION),
        ) { originalTransactionId, transactionId, historyTransaction ->
            DeviceSubcriptionState(
                originalTransactionId = originalTransactionId ?: emptyList(),
                transactionId = transactionId ?: emptyList(),
                historyTransaction = historyTransaction ?: emptyList(),
            )
        }
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
                DeviceSubcriptionState()
            )


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
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
                ConfigurationSystemState()
            )
}

