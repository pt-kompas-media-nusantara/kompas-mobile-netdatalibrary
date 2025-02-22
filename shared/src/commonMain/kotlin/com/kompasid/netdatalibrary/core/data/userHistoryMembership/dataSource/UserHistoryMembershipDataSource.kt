package com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class UserHistoryMembershipDataSource(
    private val settingsUseCase: SettingsUseCase
) {

    suspend fun save(data: UserHistoryMembershipResInterceptor) = coroutineScope {
        listOf(
            async {
                if (settingsUseCase.getString(KeySettingsType.EXPIRED_MEMBERSHIP) != data.user.expired) {
                    settingsUseCase.save(KeySettingsType.EXPIRED_MEMBERSHIP, data.user.expired)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.ACTIVE_MEMBERSHIP) != data.user.isActive) {
                    settingsUseCase.save(KeySettingsType.ACTIVE_MEMBERSHIP, data.user.isActive)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.START_DATE_MEMBERSHIP) != data.user.startDate) {
                    settingsUseCase.save(KeySettingsType.START_DATE_MEMBERSHIP, data.user.startDate)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.END_DATE_MEMBERSHIP) != data.user.endDate) {
                    settingsUseCase.save(KeySettingsType.END_DATE_MEMBERSHIP, data.user.endDate)
                }
            },
            async {
                if (settingsUseCase.getInt(KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP) != data.user.totalGracePeriod) {
                    settingsUseCase.save(
                        KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP,
                        data.user.totalGracePeriod
                    )
                }
            },
            async {
                if (settingsUseCase.getBoolean(KeySettingsType.GRACE_PERIOD_MEMBERSHIP) != data.user.gracePeriod) {
                    settingsUseCase.save(
                        KeySettingsType.GRACE_PERIOD_MEMBERSHIP,
                        data.user.gracePeriod
                    )
                }
            },
            async {
                // nurirppan__ : ini blm bisa di handle karna bukan list string tetapi list model = val active: List<HistoryMembershipResInterceptor> = emptyList(),
                settingsUseCase.save(KeySettingsType.ACTIVE_MEMBERSHIPS, data.active)
            },
            async {
                // nurirppan__ : ini blm bisa di handle karna bukan list string tetapi list model = val active: List<HistoryMembershipResInterceptor> = emptyList(),
                settingsUseCase.save(KeySettingsType.EXPIRED_MEMBERSHIPS, data.expired)
            }
        ).awaitAll()
    }
}
