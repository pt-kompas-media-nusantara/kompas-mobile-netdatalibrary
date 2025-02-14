package com.kompasid.netdatalibrary.core.domain.personalInfo.resultState

import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserHistoryMembershipResInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserDetailState() {
    private val _userDetails = MutableStateFlow<UserDetailResInterceptor?>(null)
    var userDetails: StateFlow<UserDetailResInterceptor?> = _userDetails.asStateFlow()

    fun update(data: UserDetailResInterceptor) {
        _userDetails.value = data
    }

}

