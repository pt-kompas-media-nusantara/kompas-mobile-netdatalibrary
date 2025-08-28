package com.kompasid.netdatalibrary.core.data.userHistoryMembership.mappers

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.MembershipInfoInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response.MembershipInfo
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response.UserHistory
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response.UserHistoryMembershipResponse
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response.UserMembershipResponse

fun UserMembershipResponse.toInterceptor(): UserMembershipResInterceptor {
    return UserMembershipResInterceptor(
        status = data?.status ?: data?.status ?: "",
        duration = data?.duration ?: "",
        startDate = data?.startDate ?: "",
        endDate = data?.endDate ?: "",
        gracePeriod = data?.gracePeriod ?: false,
        gracePeriodDate = data?.gracePeriodDate ?: "",
        totalGracePeriod = data?.totalGracePeriod ?: 0,
        membership = data?.membership ?: "",
        entitlement = data?.entitlement ?: "",
    )
}


