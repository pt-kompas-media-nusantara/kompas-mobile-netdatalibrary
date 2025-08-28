package com.kompasid.netdatalibrary.core.data.userHistoryMembership.mappers

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.MembershipInfoInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response.MembershipInfo
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response.UserHistory
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response.UserHistoryMembershipResponse

fun UserHistoryMembershipResponse.toInterceptor(): UserHistoryMembershipResInterceptor {
    val active = data?.active.orEmpty().filterNotNull().map { it.toInterceptor() }
    val canceled = data?.canceled.orEmpty().filterNotNull().map { it.toInterceptor() }
    val expired = data?.expired.orEmpty().filterNotNull().map { it.toInterceptor() }
    val gracePeriod = data?.gracePeriod.orEmpty().filterNotNull().map { it.toInterceptor() }

    return UserHistoryMembershipResInterceptor(
        active = active,
        canceled = canceled,
        expired = expired,
        gracePeriod = gracePeriod
    )
}


fun MembershipInfo.toInterceptor(): MembershipInfoInterceptor {
    return MembershipInfoInterceptor(
        endDate = endDate.orEmpty(),
        membershipSlug = membershipSlug.orEmpty(),
        membershipTitle = membershipTitle.orEmpty(),
        startDate = startDate.orEmpty()
    )
}