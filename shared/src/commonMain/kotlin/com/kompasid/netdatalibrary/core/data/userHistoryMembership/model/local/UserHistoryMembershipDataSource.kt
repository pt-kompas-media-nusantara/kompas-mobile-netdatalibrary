package com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.local

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserHistoryMembershipResInterceptor

class UserHistoryMembershipDataSource(
    private val settingsDataSource: SettingsDataSource
) {

    fun save(data: UserHistoryMembershipResInterceptor) {
        settingsDataSource.save(KeySettingsType.EXPIRED_MEMBERSHIP, data.user.expired)
        settingsDataSource.save(KeySettingsType.ACTIVE_MEMBERSHIP, data.user.isActive)
        settingsDataSource.save(KeySettingsType.START_DATE_MEMBERSHIP, data.user.startDate)
        settingsDataSource.save(KeySettingsType.END_DATE_MEMBERSHIP, data.user.endDate)
        settingsDataSource.save(KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP, data.user.totalGracePeriod)
        settingsDataSource.save(KeySettingsType.GRACE_PERIOD_MEMBERSHIP, data.user.gracePeriod)
        settingsDataSource.save(KeySettingsType.ACTIVE_MEMBERSHIPS, data.active)
        settingsDataSource.save(KeySettingsType.EXPIRED_MEMBERSHIPS, data.expired)
    }
}