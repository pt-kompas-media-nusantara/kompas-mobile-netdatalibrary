package com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor


data class UserMembershipHistoryResInterceptor(
    val expired: String,
    val isActive: String,
    val startDate: String,
    val endDate: String,
    val totalGracePeriod: Int,
    val gracePeriod: Boolean
)