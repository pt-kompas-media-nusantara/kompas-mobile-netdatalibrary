package com.kompasid.netdatalibrary.core.presentation.launchApp.stateState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.HistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppInterceptor
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.date.RelativeTimeFormatter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer

class LaunchAppVM(
    private val launchAppUseCase: LaunchAppUseCase,
    private val launchAppResultState: LaunchAppResultState,
    private val settingsHelper: SettingsHelper
) : BaseVM() {


    val deviceInfoState: StateFlow<DeviceInfoState> = launchAppResultState.deviceInfoState
    val deviceSubscriptionState: StateFlow<DeviceSubcriptionState> =
        launchAppResultState.deviceSubscriptionState
    val configurationSystemState: StateFlow<ConfigurationSystemState> =
        launchAppResultState.configurationSystemState

    suspend fun execute(data: LaunchAppInterceptor) {
        try {
            launchAppUseCase.execute(data)
        } catch (e: Exception) {
            // Menangani error agar tidak crash
            Logger.error { "Error executing: ${e.message}" }
        }
    }

    fun executeTest() {
        scope.launch {
            try {
                val data = LaunchAppInterceptor(
                    deviceInfoState = DeviceInfoState(
                        deviceType = "deviceType ${RelativeTimeFormatter().getCurrentTime()}",
                        osVersion = "osVersion ${RelativeTimeFormatter().getCurrentTime()}",
                        currentVersionApp = "currentVersionApp ${RelativeTimeFormatter().getCurrentTime()}",
                        newVersionApp = "newVersionApp ${RelativeTimeFormatter().getCurrentTime()}"
                    ),
                    deviceSubcriptionState = DeviceSubcriptionState(
                        originalTransactionId = listOf(
                            "1 : ${RelativeTimeFormatter().getCurrentTime()}",
                            "2 : ${RelativeTimeFormatter().getCurrentTime()}",
                        ),
                        transactionId = listOf(
                            "1 : ${RelativeTimeFormatter().getCurrentTime()}",
                            "2 : ${RelativeTimeFormatter().getCurrentTime()}",
                        ),
                        historyTransaction = listOf(
                            "1 : ${RelativeTimeFormatter().getCurrentTime()}",
                            "2 : ${RelativeTimeFormatter().getCurrentTime()}",
                        ),
                    ),
                    configurationSystemState = ConfigurationSystemState(
                        flavors = "flavors ${RelativeTimeFormatter().getCurrentTime()}",
                        isDebug = false
                    )
                )

                launchAppUseCase.execute(data)
            } catch (e: Exception) {
                Logger.error { "Error executing: ${e.message}" }
            }
        }
    }
}


