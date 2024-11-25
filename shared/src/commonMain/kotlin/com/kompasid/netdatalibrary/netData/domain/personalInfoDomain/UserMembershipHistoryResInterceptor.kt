package com.kompasid.netdatalibrary.netData.domain.personalInfoDomain


data class UserMembershipHistoryResInterceptor(
    val expired: String,
    val isActive: String,
    val startDate: String,
    val endDate: String,
    val totalGracePeriod: Int,
    val gracePeriod: Boolean
)