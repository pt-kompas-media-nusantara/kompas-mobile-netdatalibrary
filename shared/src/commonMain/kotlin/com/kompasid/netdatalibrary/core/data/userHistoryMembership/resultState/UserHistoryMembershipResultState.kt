package com.kompasid.netdatalibrary.core.data.userHistoryMembership.resultState

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class UserHistoryMembershipResultState {
    private val _userHistoryMembership =
        MutableStateFlow<UserHistoryMembershipResInterceptor?>(null)
    var userHistoryMembership: StateFlow<UserHistoryMembershipResInterceptor?> =
        _userHistoryMembership.asStateFlow()

    fun update(data: UserHistoryMembershipResInterceptor) {
        _userHistoryMembership.value = data
    }

}