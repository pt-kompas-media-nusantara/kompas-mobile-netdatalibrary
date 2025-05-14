package com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UserMembershipResponse(
    @SerialName("code")
    var code: Int? = null,
    @SerialName("data")
    var data: UserMembershipResponseData? = null,
    @SerialName("errors")
    var errors: String? = null,
    @SerialName("message")
    var message: String? = null
)

@Serializable
data class UserMembershipResponseData(
    @SerialName("access")
    var access: Boolean? = null,
    @SerialName("duration")
    var duration: String? = null,
    @SerialName("endDate")
    var endDate: String? = null,
    @SerialName("entitlement")
    var entitlement: String? = null,
    @SerialName("gracePeriod")
    var gracePeriod: Boolean? = null,
    @SerialName("gracePeriodDate")
    var gracePeriodDate: String? = null,
    @SerialName("login")
    var login: Boolean? = null,
    @SerialName("membership")
    var membership: String? = null,
    @SerialName("membershipDetail")
    var membershipDetail: String? = null,
    @SerialName("startDate")
    var startDate: String? = null,
    @SerialName("status")
    var status: String? = null,
    @SerialName("totalGracePeriod")
    var totalGracePeriod: Int? = null,
    @SerialName("user")
    var user: User? = null
)

@Serializable
data class User(
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