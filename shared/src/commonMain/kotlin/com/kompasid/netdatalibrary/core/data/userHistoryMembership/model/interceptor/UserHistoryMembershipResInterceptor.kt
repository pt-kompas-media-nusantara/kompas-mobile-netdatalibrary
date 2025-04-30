package com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response.MembershipInfo


data class UserHistoryMembershipResInterceptor(
    var active: List<MembershipInfoInterceptor>,
    var canceled: List<MembershipInfoInterceptor>,
    var expired: List<MembershipInfoInterceptor>,
    var gracePeriod: List<MembershipInfoInterceptor>,
    var user: UserHistoryInterceptor
)

data class MembershipInfoInterceptor(
    var endDate: String,
    var membershipSlug: String,
    var membershipTitle: String,
    var startDate: String
)

data class UserHistoryInterceptor(
    var email: String,
    var firstName: String,
    var guid: String,
    var lastName: String,
    var status: String,
)