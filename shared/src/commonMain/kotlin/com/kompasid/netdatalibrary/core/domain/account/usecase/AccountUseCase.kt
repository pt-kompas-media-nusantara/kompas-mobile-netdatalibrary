package com.kompasid.netdatalibrary.core.domain.account.usecase

import com.kompasid.netdatalibrary.core.domain.account.model.AccountModel
import com.kompasid.netdatalibrary.core.domain.account.data.aboutAppData
import com.kompasid.netdatalibrary.core.domain.account.data.aboutAppSubMenuData
import com.kompasid.netdatalibrary.core.domain.account.data.aboutHarianKompasData
import com.kompasid.netdatalibrary.core.domain.account.data.aboutOrganizationData
import com.kompasid.netdatalibrary.core.domain.account.data.bookmarkData
import com.kompasid.netdatalibrary.core.domain.account.data.changePasswordData
import com.kompasid.netdatalibrary.core.domain.account.data.companyHistoryData
import com.kompasid.netdatalibrary.core.domain.account.data.companyProfileData
import com.kompasid.netdatalibrary.core.domain.account.data.contactUsData
import com.kompasid.netdatalibrary.core.domain.account.data.cyberMediaGuidelinesData
import com.kompasid.netdatalibrary.core.domain.account.data.deleteAccountData
import com.kompasid.netdatalibrary.core.domain.account.data.deleteDataData
import com.kompasid.netdatalibrary.core.domain.account.data.deviceActivitiesData
import com.kompasid.netdatalibrary.core.domain.account.data.manageAccountData
import com.kompasid.netdatalibrary.core.domain.account.data.qnaData
import com.kompasid.netdatalibrary.core.domain.account.data.rewardData
import com.kompasid.netdatalibrary.core.domain.account.data.settingData
import com.kompasid.netdatalibrary.core.domain.account.data.signOutData
import com.kompasid.netdatalibrary.core.domain.account.data.subcriptionData
import com.kompasid.netdatalibrary.core.domain.account.data.termsConditionsData
import com.kompasid.netdatalibrary.core.domain.account.data.themeData
import com.kompasid.netdatalibrary.core.domain.account.enums.AccountNavigationType
import com.kompasid.netdatalibrary.helper.UserDataHelper
import com.kompasid.netdatalibrary.helper.enums.AuthFlowType
import com.kompasid.netdatalibrary.helper.enums.StateUserType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper


class AccountUseCase(
    private val userDataHelper: UserDataHelper,
) {


    suspend fun accountMenus(): List<AccountModel> {
        val checkUserType = userDataHelper.checkUserType()
        val checkAutoLogin = userDataHelper.checkAutoLogin()

        fun AccountModel.applyUserType(): AccountModel {
            return when {
                checkUserType == StateUserType.ANON && checkAutoLogin == AuthFlowType.AUTO_LOGIN -> {
                    copy(navigation = AccountNavigationType.AUTO_LOGIN)
                }

                checkUserType == StateUserType.ANON -> {
                    copy(lockIcon = "ic_lock", navigation = AccountNavigationType.SUBSCRIPTION)
                }

                else -> this
            }
        }

        return buildList {
            add(manageAccountData.applyUserType())
            add(subcriptionData.applyUserType())
            add(bookmarkData.applyUserType())
            add(rewardData) // Reward tetap sama tanpa perubahan
            add(settingData.applyUserType())

            addAll(
                listOf(
                    contactUsData,
                    qnaData,
                    aboutAppData,
                    aboutHarianKompasData
                )
            )
        }
    }


    suspend fun aboutHarianKompasMenus(): List<AccountModel> {
        return listOf(
            companyProfileData,
            companyHistoryData,
            aboutOrganizationData
        )
    }

    suspend fun aboutAppMenus(): List<AccountModel> {
        return listOf(
            aboutAppSubMenuData,
            termsConditionsData,
            cyberMediaGuidelinesData
        )
    }

    suspend fun settingMenus(): List<AccountModel> {
        return listOf(
            themeData,
            changePasswordData,
            deleteDataData,
            deviceActivitiesData,
            deleteAccountData,
            signOutData,
        )
    }


}










