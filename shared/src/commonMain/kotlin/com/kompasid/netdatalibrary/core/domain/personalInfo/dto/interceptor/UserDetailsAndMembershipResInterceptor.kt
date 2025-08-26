package com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserMembershipResInterceptor


data class UserDetailsAndMembershipResInterceptor(
    var userDetail: UserDetailResInterceptor,
    var userMembership: UserMembershipResInterceptor,
    var userHistoryMembership: UserHistoryMembershipResInterceptor,
)
