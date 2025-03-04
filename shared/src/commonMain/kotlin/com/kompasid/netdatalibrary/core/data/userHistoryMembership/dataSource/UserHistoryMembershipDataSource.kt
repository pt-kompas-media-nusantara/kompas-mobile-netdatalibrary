package com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.HistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipObjResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class UserHistoryMembershipDataSource(
    private val settingsHelper: SettingsHelper
) {

    suspend fun save(data: UserHistoryMembershipResInterceptor) = supervisorScope {
        listOf(
            KeySettingsType.EXPIRED_MEMBERSHIP to data.user.expired,
            KeySettingsType.ACTIVE_MEMBERSHIP to data.user.isActive,
            KeySettingsType.START_DATE_MEMBERSHIP to data.user.startDate,
            KeySettingsType.END_DATE_MEMBERSHIP to data.user.endDate,
            KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP to data.user.totalGracePeriod,
            KeySettingsType.GRACE_PERIOD_MEMBERSHIP to data.user.gracePeriod
        ).forEach { (key, value) ->
            launch { runCatching { settingsHelper.save(key, value) } }
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