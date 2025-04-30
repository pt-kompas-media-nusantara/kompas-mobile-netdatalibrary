package com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UserHistoryMembershipResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: UserHistoryMembershipResponseData? = null,
    @SerialName("errors")
    var errors: String? = null,
    @SerialName("message")
    var message: String? = null
)

@Serializable
data class UserHistoryMembershipResponseData(
    @SerialName("active")
    var active: List<MembershipInfo?>? = null,
    @SerialName("canceled")
    var canceled: List<MembershipInfo?>? = null,
    @SerialName("expired")
    var expired: List<MembershipInfo?>? = null,
    @SerialName("gracePeriod")
    var gracePeriod: List<MembershipInfo?>? = null,
    @SerialName("user")
    var user: UserHistory? = null
)

@Serializable
data class MembershipInfo(
    @SerialName("endDate")
    var endDate: String? = null,
    @SerialName("membershipSlug")
    var membershipSlug: String? = null,
    @SerialName("membershipTitle")
    var membershipTitle: String? = null,
    @SerialName("startDate")
    var startDate: String? = null
)

@Serializable
data class UserHistory(
    @SerialName("email")
    var email: String? = null,
    @SerialName("firstName")
    var firstName: String? = null,
    @SerialName("guid")
    var guid: String? = null,
    @SerialName("lastName")
    var lastName: String? = null,
    @SerialName("status")
    var status: String? = null
)