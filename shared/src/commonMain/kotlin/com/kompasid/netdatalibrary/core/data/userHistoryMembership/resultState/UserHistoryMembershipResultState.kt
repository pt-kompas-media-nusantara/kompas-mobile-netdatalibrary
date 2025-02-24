package com.kompasid.netdatalibrary.core.data.userHistoryMembership.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.HistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipObjResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceSubcriptionState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer


class UserHistoryMembershipResultState(
    private val settingsHelper: SettingsHelper,
) : BaseVM() {

//    private val userHistoryFlow = combine(
//        settingsHelper.getStringFlow(KeySettingsType.EXPIRED_MEMBERSHIP).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.ACTIVE_MEMBERSHIP).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.START_DATE_MEMBERSHIP).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.END_DATE_MEMBERSHIP).map { it ?: "" },
//    ) { expired, isActive, startDate, endDate ->
//        UserHistoryMembershipResInterceptor(
//            user = UserHistoryMembershipObjResInterceptor(
//                expired = expired,
//                isActive = isActive,
//                startDate = startDate,
//                endDate = endDate,
//            ),
//        )
//    }
//
//    private val userHistorySecondFlow = combine(
//        settingsHelper.getIntFlow(KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP).map { it ?: 0 },
//        settingsHelper.getBooleanFlow(KeySettingsType.GRACE_PERIOD_MEMBERSHIP).map { it ?: false },
//    ) { totalGracePeriod, gracePeriod ->
//        UserHistoryMembershipResInterceptor(
//            user = UserHistoryMembershipObjResInterceptor(
//                totalGracePeriod = totalGracePeriod,
//                gracePeriod = gracePeriod,
//            ),
//        )
//    }
//
////    private val listHistoryMembershipResInterceptor = combine(
////        settingsHelper.load(KeySettingsType.ACTIVE_MEMBERSHIPS, "", List<HistoryMembershipResInterceptor>),
////        settingsHelper.getSerializableFlow(KeySettingsType.EXPIRED_MEMBERSHIPS)
////    ) { active, expired ->
////        UserHistoryMembershipResInterceptor(
////            active = active,
////            expired = expired
////        )
////    }
//
//    val userHistoryMembershipResInterceptor: StateFlow<UserHistoryMembershipResInterceptor> =
//        combine(
//            userHistoryFlow,
//            userHistorySecondFlow,
////            listHistoryMembershipResInterceptor
//        ) { one, two ->
//            one.copy(
//                user = one.user.copy(
//                    totalGracePeriod = two.user.totalGracePeriod,
//                    gracePeriod = two.user.gracePeriod
//                ),
////                active = three.active,
////                expired = three.expired,
//            )
//        }
//            .flowOn(Dispatchers.IO)
//            .distinctUntilChanged()
//            .stateIn(
//                scope,
//                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
//                UserHistoryMembershipResInterceptor()
//            )
}

