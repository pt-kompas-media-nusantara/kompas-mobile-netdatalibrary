package com.kompasid.netdatalibrary.helper

import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.userData.MeteredWallType
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.userData.StateSubscriptionType
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.userData.UserType
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.base.UserDataTrackerModel

class UserDataHelper(
    private val settingsHelper: SettingsHelper
) {

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


