package com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class UserMembershipDataSource(
    private val settingsHelper: SettingsHelper
) {

    suspend fun save(data: UserHistoryMembershipResInterceptor): Unit = coroutineScope {
        val tasks = listOf(
            async { settingsHelper.save(KeySettingsType.EXPIRED_MEMBERSHIP, data.user.expired) },
            async { settingsHelper.save(KeySettingsType.ACTIVE_MEMBERSHIP, data.user.isActive) },
            async { settingsHelper.save(KeySettingsType.START_DATE_MEMBERSHIP, data.user.startDate) },
            async { settingsHelper.save(KeySettingsType.END_DATE_MEMBERSHIP, data.user.endDate) },
            async { settingsHelper.save(KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP, data.user.totalGracePeriod) },
            async { settingsHelper.save(KeySettingsType.GRACE_PERIOD_MEMBERSHIP, data.user.gracePeriod) }
        )

        try {
            tasks.awaitAll()
        } catch (e: Exception) {
            throw Exception("Error saving membership details", e)
        }
    }
}


//            nurirppan__ : ini error, nggak bisa save list model
//            async {
//                settingsHelper.save(
//                    KeySettingsType.ACTIVE_MEMBERSHIPS,
//                    UserHistoryMembershipResInterceptor(active = data.active).toJson()
//                )
//
////                settingsHelper.save(
////                    KeySettingsType.ACTIVE_MEMBERSHIPS,
////                    data.active[0].toJson()
////                )
//            },
//            async {
//                settingsHelper.save(
//                    KeySettingsType.EXPIRED_MEMBERSHIPS,
//                    UserHistoryMembershipResInterceptor(expired = data.expired).toJson()
//                )
////                settingsHelper.save(
////                    KeySettingsType.EXPIRED_MEMBERSHIPS,
////                    data.expired.toJson()
////                )
//            }