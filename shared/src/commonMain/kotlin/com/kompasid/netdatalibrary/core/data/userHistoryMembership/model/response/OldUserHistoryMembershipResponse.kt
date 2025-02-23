package com.kompasid.netdatalibrary.core.data.userMembershipHistoryData.dto

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
@Serializable
data class OldUserHistoryMembershipResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: OldUserHistoryMembershipResponseData? = null,
    @SerialName("message")
    var message: String? = null,
)

@Serializable
data class OldUserHistoryMembershipResponseData(
    @SerialName("result")
    var result: Result? = null
)


@Serializable
data class Result(
    @SerialName("active")
    var active: List<Active?>? = null,
    @SerialName("expired")
    var expired: List<Expired?>? = null,
//    @SerialName("gracePeriod")
//    var gracePeriod: Any? = null,
    @SerialName("user")
    var user: User? = null
)

@Serializable
data class Active(
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
data class Expired(
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
data class User(
    @SerialName("email")
    var email: String? = null,
    @SerialName("endDate")
    var endDate: String? = null,
    @SerialName("expired")
    var expired: String? = null,
    @SerialName("firstName")
    var firstName: String? = null,
    @SerialName("gracePeriod")
    var gracePeriod: Boolean? = null,
    @SerialName("isActive")
    var isActive: String? = null,
    @SerialName("lastName")
    var lastName: String? = null,
    @SerialName("startDate")
    var startDate: String? = null,
    @SerialName("totalGracePeriod")
    var totalGracePeriod: Int? = null,
//    @SerialName("updateMembership")
//    var updateMembership: Any? = null
)