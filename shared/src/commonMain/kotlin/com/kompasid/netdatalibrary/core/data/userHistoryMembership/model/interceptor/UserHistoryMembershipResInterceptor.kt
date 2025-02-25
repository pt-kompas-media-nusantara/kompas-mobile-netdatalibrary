package com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor

import kotlinx.serialization.Serializable


data class UserHistoryMembershipResInterceptor(
    var user: UserHistoryMembershipObjResInterceptor = UserHistoryMembershipObjResInterceptor(
        "",
        "",
        "",
        "",
        0,
        false,
    ),
    var active: List<HistoryMembershipResInterceptor> = mutableListOf(),
    var expired: List<HistoryMembershipResInterceptor> = emptyList(),
)

@Serializable
data class UserHistoryMembershipObjResInterceptor(
    var expired: String = "",
    var isActive: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var totalGracePeriod: Int = 0,
    var gracePeriod: Boolean = false,
)

@Serializable
data class HistoryMembershipResInterceptor(
    var membershipTitle: String = "",
    var membershipSlug: String = "",
    var startDate: String = "",
    var endDate: String = "",
)