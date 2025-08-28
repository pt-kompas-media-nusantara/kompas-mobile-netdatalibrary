package com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor

data class UserMembershipResInterceptor(
    val status: String,
    val duration: String,
    val startDate: String,
    val endDate: String,
    val gracePeriod: Boolean,
    val gracePeriodDate: String,
    val totalGracePeriod: Int,
    val membership: String,
    val entitlement: String,
)
