package com.kompasid.netdatalibrary.core.data.userHistoryMembership.mappers

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response.unitMembership.UserMembershipResponse

fun UserMembershipResponse.toInterceptor(): UserMembershipResInterceptor {
    return UserMembershipResInterceptor(
        status = data?.status ?: data?.status ?: "",
        duration = data?.duration ?: "",
        startDate = data?.startDate ?: "",
        endDate = data?.endDate ?: "",
        access = data?.access ?: false,
        gracePeriod = data?.gracePeriod ?: false,
        gracePeriodDate = data?.gracePeriodDate ?: "",
        totalGracePeriod = data?.totalGracePeriod ?: 0,
        membership = data?.membership ?: "",
        entitlement = data?.entitlement ?: "",
        guid = data?.user?.guid ?: "",
        email = data?.user?.email ?: "",
        firstName = data?.user?.firstName ?: "",
        lastName = data?.user?.lastName ?: "",
    )
}