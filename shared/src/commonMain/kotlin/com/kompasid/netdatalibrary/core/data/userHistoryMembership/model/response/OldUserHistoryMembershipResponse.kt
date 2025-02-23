package com.kompasid.netdatalibrary.core.data.userMembershipHistoryData.dto

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class OldUserHistoryMembershipResponse(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val data: OldUserHistoryMembershipResponseData? = null,
    @SerialName("message")
    val message: String? = null,
)

@Serializable
data class OldUserHistoryMembershipResponseData(
    @SerialName("result")
    val result: OldUserHistoryMembershipResponseDataResult? = null
)


@Serializable
data class OldUserHistoryMembershipResponseDataResult(
    @SerialName("active")
    val active: List<Active?>? = null,
    @SerialName("expired")
    val expired: List<Expired?>? = null,
    @SerialName("user")
    val user: User? = null
)

@Serializable
data class Active(
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("membershipSlug")
    val membershipSlug: String? = null,
    @SerialName("membershipTitle")
    val membershipTitle: String? = null,
    @SerialName("startDate")
    val startDate: String? = null
)

@Serializable
data class Expired(
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("membershipSlug")
    val membershipSlug: String? = null,
    @SerialName("membershipTitle")
    val membershipTitle: String? = null,
    @SerialName("startDate")
    val startDate: String? = null
)

@Serializable
data class User(
    @SerialName("email")
    val email: String? = null,
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("expired")
    val expired: String? = null,
    @SerialName("firstName")
    val firstName: String? = null,
    @SerialName("gracePeriod")
    val gracePeriod: Boolean? = null,
    @SerialName("isActive")
    val isActive: String? = null,
    @SerialName("lastName")
    val lastName: String? = null,
    @SerialName("startDate")
    val startDate: String? = null,
    @SerialName("totalGracePeriod")
    val totalGracePeriod: Int? = null,
)

