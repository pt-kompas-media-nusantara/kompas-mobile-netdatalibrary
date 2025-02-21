package com.kompasid.netdatalibrary.core.presentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppModel
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.core.presentation.personalInfo.deviceInfo.state.DeviceInfoResultState
import com.kompasid.netdatalibrary.core.presentation.personalInfo.deviceInfo.state.DeviceInfoState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helpers.date.RelativeTimeFormatter
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

class LaunchAppVM(
    private val launchAppUseCase: LaunchAppUseCase,
    private val deviceInfoResultState: DeviceInfoResultState,
    private val settingsUseCase: SettingsUseCase
) : BaseVM() {

    val deviceInfoState: StateFlow<DeviceInfoState> = deviceInfoResultState.state

    fun execute() {
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

//    suspend fun load() {
//        deviceInfoState.collect { state ->
//            Logger.debug { "Original Transaction ID: ${state.originalIdTransaksi}" }
//        }
//    }

    fun load() {
        this.settingsUseCase.getString(KeySettingsType.ORIGINAL_TRANSACTION_ID).toString()
    }
}