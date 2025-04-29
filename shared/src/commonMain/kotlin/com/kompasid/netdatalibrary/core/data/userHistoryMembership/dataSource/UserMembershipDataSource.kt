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

    suspend fun save(data: UserHistoryMembershipResInterceptor) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.EXPIRED_MEMBERSHIP, data.user.expired),
                settingsHelper.saveAsync(this, KeySettingsType.ACTIVE_MEMBERSHIP, data.user.isActive),
                settingsHelper.saveAsync(this, KeySettingsType.START_DATE_MEMBERSHIP, data.user.startDate),
                settingsHelper.saveAsync(this, KeySettingsType.END_DATE_MEMBERSHIP, data.user.endDate),
                settingsHelper.saveAsync(this, KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP, data.user.totalGracePeriod),
                settingsHelper.saveAsync(this, KeySettingsType.GRACE_PERIOD_MEMBERSHIP, data.user.gracePeriod)
            ).awaitAll()
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