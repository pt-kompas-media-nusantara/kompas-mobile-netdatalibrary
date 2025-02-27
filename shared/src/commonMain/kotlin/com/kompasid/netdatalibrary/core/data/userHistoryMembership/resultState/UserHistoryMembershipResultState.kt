package com.kompasid.netdatalibrary.core.data.userHistoryMembership.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.data.loginEmail.dto.interceptor.LoginInterceptor
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
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer


class UserHistoryMembershipResultState(
    private val settingsHelper: SettingsHelper,
) : BaseVM() {

    private val expired: StateFlow<String> =
        settingsHelper.load(KeySettingsType.EXPIRED_MEMBERSHIP, "")
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    private val isActive: StateFlow<String> =
        settingsHelper.load(KeySettingsType.ACTIVE_MEMBERSHIP, "")
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    private val startDate: StateFlow<String> =
        settingsHelper.load(KeySettingsType.START_DATE_MEMBERSHIP, "")
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    private val endDate: StateFlow<String> =
        settingsHelper.load(KeySettingsType.END_DATE_MEMBERSHIP, "")
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    private val totalGracePeriod: StateFlow<Int> =
        settingsHelper.load(KeySettingsType.TOTAL_GRACE_PERIOD_MEMBERSHIP, 0)
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.WhileSubscribed(5000), 0)

    private val gracePeriod: StateFlow<Boolean> =
        settingsHelper.load(KeySettingsType.GRACE_PERIOD_MEMBERSHIP, false)
            .distinctUntilChanged()
            .stateIn(scope, SharingStarted.WhileSubscribed(5000), false)

//    private val activeMemberships: StateFlow<UserHistoryMembershipResInterceptor> = settingsHelper.load(KeySettingsType.ACTIVE_MEMBERSHIPS, UserHistoryMembershipResInterceptor())
//        .distinctUntilChanged()
//        .stateIn(scope, SharingStarted.WhileSubscribed(5000), UserHistoryMembershipResInterceptor())
//
//    private val expiredMemberships: StateFlow<UserHistoryMembershipResInterceptor> = settingsHelper.load(KeySettingsType.EXPIRED_MEMBERSHIPS, UserHistoryMembershipResInterceptor())
//        .distinctUntilChanged()
//        .stateIn(scope, SharingStarted.WhileSubscribed(5000), UserHistoryMembershipResInterceptor())

    private val combinePart1 = combine(
        expired, isActive, startDate, endDate
    ) { expired, isActive, startDate, endDate ->
        UserHistoryMembershipResInterceptor(
            user = UserHistoryMembershipObjResInterceptor(
                expired = expired,
                isActive = isActive,
                startDate = startDate,
                endDate = endDate,

                )
        )
    }

    private val combinePart2 = combine(
        totalGracePeriod, gracePeriod //, activeMemberships, expiredMemberships
    ) { totalGracePeriod, gracePeriod -> // , activeMemberships, expiredMemberships
        UserHistoryMembershipResInterceptor(
            user = UserHistoryMembershipObjResInterceptor(
                totalGracePeriod = totalGracePeriod,
                gracePeriod = gracePeriod
            )
//            active = activeMemberships,
//            expired = expiredMemberships
        )
    }

    val data: StateFlow<UserHistoryMembershipResInterceptor> = combine(
        combinePart1, combinePart2
    ) { part1, part2 ->
        UserHistoryMembershipResInterceptor(
            user = UserHistoryMembershipObjResInterceptor(
                expired = part1.user.expired,
                isActive = part1.user.isActive,
                startDate = part1.user.startDate,
                endDate = part1.user.endDate,
                totalGracePeriod = part1.user.totalGracePeriod,
                gracePeriod = part2.user.gracePeriod
            ),
            active = part2.active,
            expired = part2.expired
        )
    }
        .distinctUntilChanged()
        .debounce(300)
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), UserHistoryMembershipResInterceptor())
}

