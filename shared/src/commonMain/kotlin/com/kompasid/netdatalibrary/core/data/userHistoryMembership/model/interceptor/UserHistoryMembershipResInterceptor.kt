package com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor

import kotlinx.serialization.Serializable
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
) {
    fun toJson(): String {
        return Json.encodeToString(
            serializer(), UserHistoryMembershipResInterceptor(
                user, active, expired
            )
        )
    }

    companion object {
        fun fromJSON(data: String): UserHistoryMembershipResInterceptor {
            return Json.decodeFromString(data)
        }
    }
}

@Serializable
data class UserHistoryMembershipObjResInterceptor(
    var expired: String = "",
    var isActive: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var totalGracePeriod: Int = 0,
    var gracePeriod: Boolean = false,
) {
    fun toJson(): String {
        return Json.encodeToString(
            serializer(), UserHistoryMembershipObjResInterceptor(
                expired, isActive, startDate, endDate, totalGracePeriod, gracePeriod
            )
        )
    }

    companion object {
        fun fromJSON(data: String): UserHistoryMembershipObjResInterceptor {
            return Json.decodeFromString(data)
        }
    }
}

@Serializable
data class HistoryMembershipResInterceptor(
    var membershipTitle: String = "",
    var membershipSlug: String = "",
    var startDate: String = "",
    var endDate: String = "",
) {
    fun toJson(): String {
        return Json.encodeToString(
            serializer(), HistoryMembershipResInterceptor(
                membershipTitle, membershipSlug, startDate, endDate
            )
        )
    }

    companion object {
        fun fromJSON(data: String): HistoryMembershipResInterceptor {
            return Json.decodeFromString(data)
        }
    }
}