package com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class UserHistoryMembershipDataSource(
    private val settingsHelper: SettingsHelper
) {

    suspend fun save(data: UserHistoryMembershipResInterceptor) = coroutineScope {
        listOf(
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.EXPIRED_MEMBERSHIP).value != data.user.expired) {
                    settingsHelper.save(KeySettingsType.EXPIRED_MEMBERSHIP, data.user.expired)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.ACTIVE_MEMBERSHIP).value != data.user.isActive) {
                    settingsHelper.save(KeySettingsType.ACTIVE_MEMBERSHIP, data.user.isActive)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.START_DATE_MEMBERSHIP).value != data.user.startDate) {
                    settingsHelper.save(KeySettingsType.START_DATE_MEMBERSHIP, data.user.startDate)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.END_DATE_MEMBERSHIP).value != data.user.endDate) {
                    settingsHelper.save(KeySettingsType.END_DATE_MEMBERSHIP, data.user.endDate)
                }
            },
            async {
                if (settingsHelper.getIntFlow(KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP).value != data.user.totalGracePeriod) {
                    settingsHelper.save(
                        KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP,
                        data.user.totalGracePeriod
                    )
                }
            },
            async {
                if (settingsHelper.getBooleanFlow(KeySettingsType.GRACE_PERIOD_MEMBERSHIP).value != data.user.gracePeriod) {
                    settingsHelper.save(
                        KeySettingsType.GRACE_PERIOD_MEMBERSHIP,
                        data.user.gracePeriod
                    )
                }
            },
            async {
                // nurirppan__ : ini blm bisa di handle karna bukan list string tetapi list model = val active: List<HistoryMembershipResInterceptor> = emptyList(),
                settingsHelper.save(KeySettingsType.ACTIVE_MEMBERSHIPS, data.active)
            },
            async {
                // nurirppan__ : ini blm bisa di handle karna bukan list string tetapi list model = val active: List<HistoryMembershipResInterceptor> = emptyList(),
                settingsHelper.save(KeySettingsType.EXPIRED_MEMBERSHIPS, data.expired)
            }
        ).awaitAll()
    }
}
