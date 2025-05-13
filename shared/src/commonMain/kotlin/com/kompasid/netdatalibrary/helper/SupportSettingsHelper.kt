package com.kompasid.netdatalibrary.helper

import com.kompasid.netdatalibrary.helper.enums.AuthFlowType
import com.kompasid.netdatalibrary.helper.enums.StateInstallType
import com.kompasid.netdatalibrary.helper.enums.StateUserType
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.userData.MeteredWallType
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.userData.StateSubscriptionType
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.userData.UserType
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.base.UserDataTrackerModel

class SupportSettingsHelper(
    private val settingsHelper: SettingsHelper
) {

    suspend fun getAccessRefreshToken(): AccessRefreshToken {
        val accessToken: String = settingsHelper.get(KeySettingsType.ACCESS_TOKEN, "")
        val refreshToken: String = settingsHelper.get(KeySettingsType.REFRESH_TOKEN, "")

        return AccessRefreshToken(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    suspend fun stateInstallType(): StateInstallType {
        val stateInstall: Int = settingsHelper.get(KeySettingsType.STATE_INSTALL, 0)
        if (stateInstall == 2) {
            return StateInstallType.UPDATED
        }
        return StateInstallType.FIRST_INSTALL
    }

    // (hit api dulu, untuk mengecek apakah apulo yang muncul atau auto login)
    // - jika user mempunyai purchase token baik aktif maupun tidak aktif akan menampilkan auto login.
    // - jika user tidak mempunyai purchase token akan menampilkan halaman login / register / berlangganan tergantung entry point yang di click
    // - jika user regon dan mempunyai purchase token aktif, dimana purchase token tersebut tidak mempunyai guid. maka akan menampilkan apulo
    suspend fun checkAuthScreenType(): AuthFlowType {
        val isAutoLogin: Boolean = settingsHelper.get(KeySettingsType.IS_AUTO_LOGIN, false)
        val oriTrxId: List<String> = settingsHelper.get(KeySettingsType.ORIGINAL_TRANSACTION_ID, emptyList())

        return when {
            isAutoLogin -> AuthFlowType.AUTO_LOGIN
            oriTrxId.isNotEmpty() && !isAutoLogin -> AuthFlowType.APULO
            else -> AuthFlowType.NEXT
        }
    }

    suspend fun isRegisterWallShown(): Boolean {
        return checkUserType() == StateUserType.ANON
    }

    suspend fun checkUserType(): StateUserType {
        val subscriptionStatus: String = settingsHelper.get(KeySettingsType.SUBSCRIPTION_STATUS, "")
        val gracePeriod: Boolean = settingsHelper.get(KeySettingsType.GRACE_PERIOD, false)
        val suber: String = "Aktif Berlangganan"
        if (subscriptionStatus.lowercase() == suber.lowercase()) {
            return StateUserType.SUBER
        } else if (gracePeriod) {
            return StateUserType.GRACE_PERIOD
        } else if (subscriptionStatus != suber && !gracePeriod) {
            return StateUserType.REGON
        } else {
            return StateUserType.ANON
        }
    }

    suspend fun userDataTracker(): UserDataTrackerModel {
        return UserDataTrackerModel(
            UserType.ANONYMOUS.value,
            StateSubscriptionType.ACTIVE.value,
            MeteredWallType.HP.value,
            "0", // harus di kalkulasi dati api dan ambil dari seeting use case
            "Kompas.id",
            "", // harus di kalkulasi dati api dan ambil dari seeting use case
        )
    }
}


data class AccessRefreshToken(
    val accessToken: String,
    val refreshToken: String
)