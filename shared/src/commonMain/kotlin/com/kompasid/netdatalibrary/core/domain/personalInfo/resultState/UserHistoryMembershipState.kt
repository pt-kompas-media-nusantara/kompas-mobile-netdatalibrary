//package com.kompasid.netdatalibrary.core.domain.personalInfo.resultState
//
//import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//
//class UserHistoryMembershipState {
//
//    private val _historyMembership = MutableStateFlow<UserHistoryMembershipResInterceptor?>(null)
//    var historyMembership: StateFlow<UserHistoryMembershipResInterceptor?> =
//        _historyMembership.asStateFlow()
//
//    fun update(data: UserHistoryMembershipResInterceptor) {
//        _historyMembership.value = data
//    }
//}