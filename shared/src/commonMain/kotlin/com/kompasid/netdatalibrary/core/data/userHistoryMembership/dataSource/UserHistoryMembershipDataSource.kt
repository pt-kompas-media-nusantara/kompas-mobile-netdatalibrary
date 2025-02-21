package com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType

class UserHistoryMembershipDataSource(
    private val settingsUseCase: SettingsUseCase
) {

    suspend fun save(data: UserHistoryMembershipResInterceptor) {
        settingsUseCase.save(KeySettingsType.EXPIRED_MEMBERSHIP, data.user.expired)
        settingsUseCase.save(KeySettingsType.ACTIVE_MEMBERSHIP, data.user.isActive)
        settingsUseCase.save(KeySettingsType.START_DATE_MEMBERSHIP, data.user.startDate)
        settingsUseCase.save(KeySettingsType.END_DATE_MEMBERSHIP, data.user.endDate)
        settingsUseCase.save(KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP, data.user.totalGracePeriod)
        settingsUseCase.save(KeySettingsType.GRACE_PERIOD_MEMBERSHIP, data.user.gracePeriod)
        settingsUseCase.save(KeySettingsType.ACTIVE_MEMBERSHIPS, data.active)
        settingsUseCase.save(KeySettingsType.EXPIRED_MEMBERSHIPS, data.expired)
    }
}