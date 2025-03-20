package com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class UserHistoryMembershipResInterceptor(
    var user: UserHistoryMembershipObjResInterceptor = UserHistoryMembershipObjResInterceptor(
        "",
        "",
        "",
        "",
        0,
        false,
    ),
    var active: List<HistoryMembershipResInterceptor> = emptyList(),
    var expired: List<HistoryMembershipResInterceptor> = emptyList(),
)

@Serializable
data class UserHistoryMembershipObjResInterceptor(
    var expired: String = "",
    var isActive: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var totalGracePeriod: Int = 99,
    var gracePeriod: Boolean = false,
)

@Serializable
data class HistoryMembershipResInterceptor(
    var membershipTitle: String = "",
    var membershipSlug: String = "",
    var startDate: String = "",
    var endDate: String = "",
)