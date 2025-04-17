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

    suspend fun stateInstallType(): StateInstallType {
        val stateInstall: Int = settingsHelper.get(KeySettingsType.STATE_INSTALL, 0)
        if (stateInstall != 2) {
            return StateInstallType.FIRST_INSTALL
        }
        return StateInstallType.UPDATED
    }

    suspend fun checkUserType(): StateUserType {
        val isActive: String = settingsHelper.get(KeySettingsType.IS_ACTIVE, "")
        val gracePeriod: Int = settingsHelper.get(KeySettingsType.GRACE_PERIOD_MEMBERSHIP, 0)
        val suber: String = "Aktif Berlangganan"
        if (isActive.lowercase() == suber.lowercase()) {
            return StateUserType.SUBER
        } else if (gracePeriod > 0) {
            return StateUserType.GRACE_PERIOD
        } else if (isActive != suber && gracePeriod == 0) {
            return StateUserType.REGON
        } else {
            return StateUserType.ANON
        }
    }

    suspend fun checkAutoLogin(): AuthFlowType {
        val isActive: String = settingsHelper.get(KeySettingsType.IS_ACTIVE, "")
//        val originalTransactionId: String = settingsHelper.get(KeySettingsType.ORIGINAL_TRANSACTION_ID, "")
        val gracePeriod: Int = settingsHelper.get(KeySettingsType.GRACE_PERIOD_MEMBERSHIP, 0)
        val suber: String = "Aktif Berlangganan"

        if (isActive.lowercase() == suber.lowercase()) {
            return AuthFlowType.NEXT
        } else if (isActive.lowercase() != suber.lowercase()) {
//            && originalTransactionId.isNotEmpty()
            // nurirppan : harusnya pas membershipnya active bukan kosong
            return AuthFlowType.AUTO_LOGIN
        } else {
            return AuthFlowType.AUTH
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


