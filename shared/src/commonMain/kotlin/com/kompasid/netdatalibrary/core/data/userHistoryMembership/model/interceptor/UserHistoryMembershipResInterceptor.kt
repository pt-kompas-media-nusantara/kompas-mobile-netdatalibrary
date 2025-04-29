package com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class UserHistoryMembershipResInterceptor(
    var user: UserHistoryMembershipObjResInterceptor,
    var active: List<HistoryMembershipResInterceptor>,
    var expired: List<HistoryMembershipResInterceptor>,
)

data class UserHistoryMembershipObjResInterceptor(
    var expired: String,
    var isActive: String,
    var startDate: String,
    var endDate: String,
    var totalGracePeriod: Int,
    var gracePeriod: Boolean,
)

data class HistoryMembershipResInterceptor(
    var membershipTitle: String,
    var membershipSlug: String,
    var startDate: String,
    var endDate: String,
)