package com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor

import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.interceptor.CheckVerifiedUserResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor


data class PersonalInfoInterceptor(
    val userDetails: UserDetailResInterceptor = UserDetailResInterceptor(),
    val userHistoryMembership: UserHistoryMembershipResInterceptor = UserHistoryMembershipResInterceptor(),
    val checkVerifiedUser: CheckVerifiedUserResInterceptor = CheckVerifiedUserResInterceptor()
)

