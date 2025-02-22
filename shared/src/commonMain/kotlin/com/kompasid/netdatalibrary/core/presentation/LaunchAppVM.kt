package com.kompasid.netdatalibrary.core.presentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppModel
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.core.presentation.launchApp.state.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.state.LaunchAppResultState
import com.kompasid.netdatalibrary.core.presentation.launchApp.state.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.state.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helpers.date.RelativeTimeFormatter
import kotlinx.coroutines.flow.StateFlow

class LaunchAppVM(
    private val launchAppUseCase: LaunchAppUseCase,
    private val launchAppResultState: LaunchAppResultState,
) : BaseVM() {

    val deviceInfoState: StateFlow<DeviceInfoState> = launchAppResultState.deviceInfoState
    val deviceSubscriptionState: StateFlow<DeviceSubcriptionState> = launchAppResultState.deviceSubscriptionState
    val configurationSystemState: StateFlow<ConfigurationSystemState> = launchAppResultState.configurationSystemState


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


