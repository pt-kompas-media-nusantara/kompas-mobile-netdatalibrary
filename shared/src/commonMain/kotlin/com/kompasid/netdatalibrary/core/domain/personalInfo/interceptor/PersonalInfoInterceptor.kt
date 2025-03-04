package com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor

import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map


data class PersonalInfoInterceptor(
    val userDetails: UserDetailResInterceptor = UserDetailResInterceptor(),
    val userHistoryMembership: UserHistoryMembershipResInterceptor = UserHistoryMembershipResInterceptor()
)
