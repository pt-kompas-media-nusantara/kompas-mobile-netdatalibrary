package com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor


data class UserHistoryMembershipResInterceptor(
    val user: UserHistoryMembershipObjResInterceptor,
    val active: List<HistoryMembershipResInterceptor>,
    val expired: List<HistoryMembershipResInterceptor>,
)

data class UserHistoryMembershipObjResInterceptor(
    val expired: String,
    val isActive: String,
    val startDate: String,
    val endDate: String,
    val totalGracePeriod: Int,
    val gracePeriod: Boolean,
)

data class HistoryMembershipResInterceptor(
    val membershipTitle: String,
    val membershipSlug: String,
    val startDate: String,
    val endDate: String,
)