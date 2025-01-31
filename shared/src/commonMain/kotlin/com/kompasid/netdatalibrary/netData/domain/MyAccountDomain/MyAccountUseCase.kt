package com.kompasid.netdatalibrary.netData.domain.MyAccountDomain

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.UserDataHelper
import com.kompasid.netdatalibrary.netData.domain.SettingsDomain.SettingsUseCase
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.EventName
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerManager
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.TrackerUseCase
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.AuthenticationEntryPoint
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.OpenFromEntryPoint
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.SignUpStartedModel


class MyAccountUseCase(
    private val settingsUseCase: SettingsUseCase,
    private var trackerManager: TrackerManager,
    private var userDataHelper: UserDataHelper,
) {

    suspend fun stateUserType(): StateUserType {
        val email =
            settingsUseCase.getStringDataSource(KeySettingsType.EMAIL) // kompastesting16@yopmail.com
        val stateMembership =
            settingsUseCase.getStringDataSource(KeySettingsType.MEMBERSHIP_ACTIVE) // Aktif Berlangganan
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










