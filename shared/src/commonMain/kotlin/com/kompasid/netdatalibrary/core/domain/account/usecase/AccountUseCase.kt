package com.kompasid.netdatalibrary.core.domain.account.usecase

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.core.domain.account.model.AccountModel
import com.kompasid.netdatalibrary.core.domain.account.model.MyAccountInformationModel
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
import com.kompasid.netdatalibrary.core.domain.account.data.termsConditionsData
import com.kompasid.netdatalibrary.core.domain.account.data.themeData
import com.kompasid.netdatalibrary.core.domain.account.model.StateUserType
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase


class AccountUseCase(
    private val settingsUseCase: SettingsUseCase
) {

    suspend fun userType(): StateUserType {
        val email = settingsUseCase.getStringDataSource(KeySettingsType.EMAIL) // kompastesting16@yopmail.com
        val stateMembership = settingsUseCase.getStringDataSource(KeySettingsType.ACTIVE_MEMBERSHIP) // Aktif Berlangganan
        // val stateGracePeriod = settingsUseCase.getBooleanDataSource(KeySettingsType.GRACE_PERIOD_MEMBERSHIP) // false | nurirppan_: kalau grace periode >0 harusnya apa ?

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
            settingsUseCase.getStringDataSource(KeySettingsType.EXPIRED_MEMBERSHIP) // 21 Jan 2022 - 18 Feb 2038
        val stateMembership =
            settingsUseCase.getStringDataSource(KeySettingsType.ACTIVE_MEMBERSHIP) // Aktif Berlangganan

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










