package com.kompasid.netdatalibrary.core.domain.myAccount.usecase

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.AccountModel
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.MyAccountInformationModel
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.StateUserType
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.aboutAppData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.aboutAppSubMenuData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.aboutHarianKompasData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.aboutOrganizationData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.bookmarkData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.changePasswordData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.companyHistoryData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.companyProfileData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.contactUsData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.cyberMediaGuidelinesData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.deleteAccountData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.deleteDataData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.deviceActivitiesData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.manageAccountData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.qnaData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.rewardData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.settingData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.signOutData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.termsConditionsData
import com.kompasid.netdatalibrary.netData.domain.MyAccountDomain.themeData


class MyAccountUseCase(
    private val settingsUseCase: SettingsUseCase
) {

    suspend fun stateUserType(): StateUserType {
        val email = settingsUseCase.getStringDataSource(KeySettingsType.EMAIL) // kompastesting16@yopmail.com
        val stateMembership = settingsUseCase.getStringDataSource(KeySettingsType.MEMBERSHIP_ACTIVE) // Aktif Berlangganan
        // val stateGracePeriod = settingsUseCase.getBooleanDataSource(KeySettingsType.MEMBERSHIP_GRACE_PERIOD) // false

        if (email.isEmpty()) {
            return StateUserType.ANON
        } else if (email.isNotEmpty() && stateMembership.lowercase() == "tidak berlangganan") {
            return StateUserType.REGON
        } else {
            return StateUserType.SUBER
        }
    }

    suspend fun myAccountInformation(): MyAccountInformationModel {
        val firstName = settingsUseCase.getStringDataSource(KeySettingsType.FIRST_NAME) // Nurirp
        val lastName = settingsUseCase.getStringDataSource(KeySettingsType.LAST_NAME) // Pangestu
        val dateExpired =
            settingsUseCase.getStringDataSource(KeySettingsType.MEMBERSHIP_EXPIRED) // 21 Jan 2022 - 18 Feb 2038
        val stateMembership =
            settingsUseCase.getStringDataSource(KeySettingsType.MEMBERSHIP_ACTIVE) // Aktif Berlangganan

        val strings = listOf(firstName, lastName) // Output: "NP"
        val idUserName = strings.joinToString(separator = "") { it.firstOrNull()?.toString() ?: "" }

        return MyAccountInformationModel(
            idUserName,
            firstName,
            lastName,
            dateExpired,
            stateMembership
        )
    }

    suspend fun accountMenus(): List<AccountModel> {
        return listOf(
            manageAccountData,
            bookmarkData,
            rewardData,
            settingData,
            contactUsData,
            qnaData,
            aboutAppData,
            aboutHarianKompasData,
        )
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










