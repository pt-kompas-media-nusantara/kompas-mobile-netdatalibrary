package com.kompasid.netdatalibrary.android

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppModel
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.helpers.date.RelativeTimeFormatter
import kotlinx.coroutines.launch

class LaunchAppVM(
    private val launchAppUseCase: LaunchAppUseCase
) : BaseVM() {

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
}