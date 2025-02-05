package com.kompasid.netdatalibrary.helper

import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.userData.MeteredWallType
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.userData.StateSubscriptionType
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums.userData.UserType
import com.kompasid.netdatalibrary.netData.domain.trackerDomain.model.base.UserDataTrackerModel

class UserDataHelper(
    private val settingsUseCase: SettingsUseCase
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


