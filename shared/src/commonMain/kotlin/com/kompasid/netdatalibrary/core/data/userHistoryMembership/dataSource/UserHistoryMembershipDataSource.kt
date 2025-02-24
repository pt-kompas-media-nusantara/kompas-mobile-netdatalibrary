package com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.HistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

class UserHistoryMembershipDataSource(
    private val settingsHelper: SettingsHelper
) {

    suspend fun save(data: UserHistoryMembershipResInterceptor) = coroutineScope {
        listOf(
            async {
                settingsHelper.save(KeySettingsType.EXPIRED_MEMBERSHIP, data.user.expired)
            },
            async {
                settingsHelper.save(KeySettingsType.ACTIVE_MEMBERSHIP, data.user.isActive)
            },
            async {
                settingsHelper.save(KeySettingsType.START_DATE_MEMBERSHIP, data.user.startDate)
            },
            async {
                settingsHelper.save(KeySettingsType.END_DATE_MEMBERSHIP, data.user.endDate)
            },
            async {
                settingsHelper.save(
                    KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP,
                    data.user.totalGracePeriod
                )
            },
            async {
                settingsHelper.save(KeySettingsType.GRACE_PERIOD_MEMBERSHIP, data.user.gracePeriod)
            },
            async {
                settingsHelper.save(
                    KeySettingsType.ACTIVE_MEMBERSHIPS,
                    data.active,
                    ListSerializer(HistoryMembershipResInterceptor.serializer())
                )
            },
            async {
                settingsHelper.save(
                    KeySettingsType.EXPIRED_MEMBERSHIPS,
                    data.expired,
                    ListSerializer(HistoryMembershipResInterceptor.serializer())
                )
            }
        ).awaitAll()
    }
}
