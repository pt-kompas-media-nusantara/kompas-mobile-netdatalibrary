package com.kompasid.netdatalibrary.core.data.userMembershipHistory.model.local

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource

class UserMembershipHistoryDataSource(
    private val settingsDataSource: SettingsDataSource
) {
    fun save(
        expired: String,
        active: String,
        startDate: String,
        endDate: String,
        totalGracePeriod: Int,
        gracePeriod: Boolean
    ) {
        settingsDataSource.save(KeySettingsType.MEMBERSHIP_EXPIRED, expired)
        settingsDataSource.save(KeySettingsType.MEMBERSHIP_ACTIVE, active)
        settingsDataSource.save(KeySettingsType.MEMBERSHIP_START_DATE, startDate)
        settingsDataSource.save(KeySettingsType.MEMBERSHIP_END_DATE, endDate)
        settingsDataSource.save(KeySettingsType.MEMBERSHIP_TOTAL_GRACE_PERIOD, totalGracePeriod)
        settingsDataSource.save(KeySettingsType.MEMBERSHIP_GRACE_PERIOD, gracePeriod)
    }
}

