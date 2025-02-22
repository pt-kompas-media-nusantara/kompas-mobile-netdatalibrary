package com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor


data class UserHistoryMembershipResInterceptor(
    val user: UserHistoryMembershipObjResInterceptor = UserHistoryMembershipObjResInterceptor(
        "",
        "",
        "",
        "",
        0,
        false,
    ),
    val active: List<HistoryMembershipResInterceptor> = emptyList(),
    val expired: List<HistoryMembershipResInterceptor> = emptyList(),
)

data class UserHistoryMembershipObjResInterceptor(
    val expired: String = "",
    val isActive: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val totalGracePeriod: Int = 0,
    val gracePeriod: Boolean = false,
)

data class HistoryMembershipResInterceptor(
    val membershipTitle: String = "",
    val membershipSlug: String = "",
    val startDate: String = "",
    val endDate: String = "",
)