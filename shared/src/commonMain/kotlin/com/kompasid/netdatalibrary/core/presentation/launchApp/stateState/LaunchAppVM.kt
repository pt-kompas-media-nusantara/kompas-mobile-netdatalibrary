package com.kompasid.netdatalibrary.core.presentation.launchApp.stateState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.domain.account.usecase.AccountUseCase
import com.kompasid.netdatalibrary.core.domain.articleList.usecase.ArticleListUseCase
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import com.kompasid.netdatalibrary.core.domain.forceUpdate.useCase.ForceUpdateUseCase
import com.kompasid.netdatalibrary.core.domain.generalContent.usecase.GeneralContentUseCase
import com.kompasid.netdatalibrary.core.domain.launchApp.model.LaunchAppInterceptor
import com.kompasid.netdatalibrary.core.domain.launchApp.useCase.LaunchAppUseCase
import com.kompasid.netdatalibrary.core.domain.myRubriks.useCase.MyRubriksUseCase
import com.kompasid.netdatalibrary.core.domain.osRecomendation.useCase.OSRecomendationUseCase
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import com.kompasid.netdatalibrary.core.domain.token.usecase.TokenUseCase
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.ConfigurationSystemState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helper.SupportSettingsHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.flow.StateFlow

class LaunchAppVM(
    private val accountUseCase: AccountUseCase,
    private val articleListUseCase: ArticleListUseCase,
    private val authUseCase: AuthUseCase,
    private val forceUpdateUseCase: ForceUpdateUseCase,
    private val generalContentUseCase: GeneralContentUseCase,
    private val launchAppUseCase: LaunchAppUseCase,
    private val myRubriksUseCase: MyRubriksUseCase,
    private val osRecomendationUseCase: OSRecomendationUseCase,
    private val personalInfoUseCase: PersonalInfoUseCase,
    private val tokenUseCase: TokenUseCase,
    private val settingsHelper: SettingsHelper,
    private val supportSettingsHelper: SupportSettingsHelper
) : BaseVM() {


//    val deviceInfoState: StateFlow<DeviceInfoState> = launchAppResultState.deviceInfoState
//    val deviceSubscriptionState: StateFlow<DeviceSubcriptionState> =
//        launchAppResultState.deviceSubscriptionState
//    val configurationSystemState: StateFlow<ConfigurationSystemState> =
//        launchAppResultState.configurationSystemState

    suspend fun execute(data: LaunchAppInterceptor) {
        try {
            launchAppUseCase.execute(data)
        } catch (e: Exception) {
            // Menangani error agar tidak crash
            Logger.error { "Error executing: ${e.message}" }
        }
    }

    fun executeTest() {
//        scope.launch {
//            try {
//                val data = LaunchAppInterceptor(
//                    deviceInfoState = DeviceInfoState(
//                        deviceType = "deviceType ${RelativeTimeFormatter().getCurrentTime()}",
//                        osVersion = "osVersion ${RelativeTimeFormatter().getCurrentTime()}",
//                        currentVersionApp = "currentVersionApp ${RelativeTimeFormatter().getCurrentTime()}",
//                        newVersionApp = "newVersionApp ${RelativeTimeFormatter().getCurrentTime()}"
//                    ),
//                    deviceSubcriptionState = DeviceSubcriptionState(
//                        originalTransactionId = listOf(
//                            "1 : ${RelativeTimeFormatter().getCurrentTime()}",
//                            "2 : ${RelativeTimeFormatter().getCurrentTime()}",
//                        ),
//                        transactionId = listOf(
//                            "1 : ${RelativeTimeFormatter().getCurrentTime()}",
//                            "2 : ${RelativeTimeFormatter().getCurrentTime()}",
//                        ),
//                        historyTransaction = listOf(
//                            "1 : ${RelativeTimeFormatter().getCurrentTime()}",
//                            "2 : ${RelativeTimeFormatter().getCurrentTime()}",
//                        ),
//                    ),
//                    configurationSystemState = ConfigurationSystemState(
//                        flavors = "flavors ${RelativeTimeFormatter().getCurrentTime()}",
//                        isDebug = false
//                    )
//                )
//
//                launchAppUseCase.execute(data)
//            } catch (e: Exception) {
//                Logger.error { "Error executing: ${e.message}" }
//            }
//        }
    }
}


