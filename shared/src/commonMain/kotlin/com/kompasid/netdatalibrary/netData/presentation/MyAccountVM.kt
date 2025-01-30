package com.kompasid.netdatalibrary.netData.presentation

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.MyAccountUseCase
import kotlinx.coroutines.launch

class MyAccountVM(
    private val myAccountUseCase: MyAccountUseCase
): BaseVM() {

    fun accountList() {
        scope.launch {
            val resultMyAccountInformation = myAccountUseCase.myAccountInformation()
            Logger.debug { resultMyAccountInformation.toString() }

            val resultAccountMenus = myAccountUseCase.accountMenus()
            Logger.debug { resultAccountMenus.toString() }

            val resultAboutHarianKompasMenus = myAccountUseCase.aboutHarianKompasMenus()
            Logger.debug { resultAboutHarianKompasMenus.toString() }

            val resultAboutAppMenus = myAccountUseCase.aboutAppMenus()
            Logger.debug { resultAboutAppMenus.toString() }

            val resultSettingMenus = myAccountUseCase.settingMenus()
            Logger.debug { resultSettingMenus.toString() }
        }
    }
}