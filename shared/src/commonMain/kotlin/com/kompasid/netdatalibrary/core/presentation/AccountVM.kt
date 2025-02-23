package com.kompasid.netdatalibrary.core.presentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.domain.auth.usecase.AuthUseCase
import com.kompasid.netdatalibrary.core.domain.generalContent.usecase.GeneralContentUseCase
import com.kompasid.netdatalibrary.core.domain.manageAccount.useCase.ManageAccountUseCase
import com.kompasid.netdatalibrary.core.domain.account.usecase.AccountUseCase
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import com.kompasid.netdatalibrary.core.domain.token.usecase.TokenUseCase
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerUseCase
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.OpenFromEntryPoint
import kotlinx.coroutines.launch

class AccountVM(
    private val accountUseCase: AccountUseCase,
    private val trackerUseCase: TrackerUseCase
) : BaseVM() {

    fun onAppear() {
        scope.launch {
            trackerUseCase.pageViewed(OpenFromEntryPoint.MyAccount)
        }
    }

    fun accountMenus() {
        scope.launch {
            val resultaccountMenus = accountUseCase.accountMenus()
            Logger.debug { resultaccountMenus.toString() }

            val resultaboutHarianKompasMenus = accountUseCase.aboutHarianKompasMenus()
            Logger.debug { resultaboutHarianKompasMenus.toString() }

            val resultaboutAppMenus = accountUseCase.aboutAppMenus()
            Logger.debug { resultaboutAppMenus.toString() }

            val resultsettingMenus = accountUseCase.settingMenus()
            Logger.debug { resultsettingMenus.toString() }

        }
    }

}