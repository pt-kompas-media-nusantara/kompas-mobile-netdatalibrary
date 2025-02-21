package com.kompasid.netdatalibrary.core.data.userDetail.resultState

import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class UserDetailResultState {
    private val _userDetail = MutableStateFlow<UserDetailResInterceptor?>(null)
    var userDetail: StateFlow<UserDetailResInterceptor?> = _userDetail.asStateFlow()

    suspend fun updateUserDetail(data: UserDetailResInterceptor) {
        _userDetail.value = data
    }

}