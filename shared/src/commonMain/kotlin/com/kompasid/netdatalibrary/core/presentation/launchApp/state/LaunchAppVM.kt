package com.kompasid.netdatalibrary.core.presentation.launchApp.state

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppModel
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helpers.date.RelativeTimeFormatter
import kotlinx.coroutines.flow.StateFlow

class LaunchAppVM(
    private val launchAppUseCase: LaunchAppUseCase,
    private val launchAppResultState: LaunchAppResultState,
) : BaseVM() {

    val deviceInfoState: StateFlow<DeviceInfoState> = launchAppResultState.deviceInfoState
    val deviceSubscriptionState: StateFlow<DeviceSubcriptionState> =
        launchAppResultState.deviceSubscriptionState
    val configurationSystemState: StateFlow<ConfigurationSystemState> =
        launchAppResultState.configurationSystemState


    suspend fun execute() {
        try {
            val data = LaunchAppModel(
                "flavors ${RelativeTimeFormatter().getCurrentTime()}",
                listOf("originalTransactionId ${RelativeTimeFormatter().getCurrentTime()}"), // nurirppan__ : ini list stringmasih ngebug
                listOf("transactionId ${RelativeTimeFormatter().getCurrentTime()}"),
                "deviceType ${RelativeTimeFormatter().getCurrentTime()}",
                "osVersion ${RelativeTimeFormatter().getCurrentTime()}",
                "currentVersionApp ${RelativeTimeFormatter().getCurrentTime()}",
                "newVersionApp ${RelativeTimeFormatter().getCurrentTime()}",
                listOf("historyTransaction ${RelativeTimeFormatter().getCurrentTime()}"),
                true,
            )

            launchAppUseCase.execute(data)
        } catch (e: Exception) {
            // Menangani error agar tidak crash
            Logger.error { "Error executing: ${e.message}" }
        }
    }
}


