package com.kompasid.netdatalibrary.core.data.userMembershipHistoryData.dto

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class OldUserHistoryMembershipResponse(
    @SerialName("result")
    val result: Result? = null,
)


@Serializable
data class Result(
    @SerialName("active")
    val active: List<OldActive>? = null,
    @SerialName("expired")
    val expired: List<OldExpired>? = null,
    @SerialName("user")
    val user: OldUser? = null,
)

@Serializable
data class OldActive(
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("membershipSlug")
    val membershipSlug: String? = null,
    @SerialName("membershipTitle")
    val membershipTitle: String? = null,
    @SerialName("startDate")
    val startDate: String? = null,
)

@Serializable
data class OldExpired(
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("membershipSlug")
    val membershipSlug: String? = null,
    @SerialName("membershipTitle")
    val membershipTitle: String? = null,
    @SerialName("startDate")
    val startDate: String? = null,
)

@Serializable
data class OldUser(
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
//    @SerialName("updateMembership")
//    val updateMembership: Any?
)



