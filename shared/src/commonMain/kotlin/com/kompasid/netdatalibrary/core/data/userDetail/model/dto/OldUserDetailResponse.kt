package com.kompasid.netdatalibrary.core.data.userDetailData.dto

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class OldUserDetailResponse(
    @SerialName("city")
    val city: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("countryCode")
    val countryCode: String? = null,
    @SerialName("createDate")
    val createDate: OldCreateDate? = null,
    @SerialName("dateBirth")
    val dateBirth: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("firstName")
    val firstName: String? = null,
    @SerialName("gender")
    val gender: Int? = null,
    @SerialName("isActive")
    val isActive: Boolean? = null,
    @SerialName("isMigrate")
    val isMigrate: Boolean? = null,
    @SerialName("isPassEmpty")
    val isPassEmpty: Boolean? = null,
    @SerialName("isSocial")
    val isSocial: Boolean? = null,
    @SerialName("isVVIP")
    val isVVIP: Boolean? = null,
    @SerialName("lastName")
    val lastName: String? = null,
    @SerialName("phoneNumber")
    val phoneNumber: String? = null,
    @SerialName("province")
    val province: String? = null,
    @SerialName("userCompleted")
    val userCompleted: Boolean? = null,
    @SerialName("userGuid")
    val userGuid: String? = null,
    @SerialName("userId")
    val userId: String? = null,
    @SerialName("userStatus")
    val userStatus: OldUserStatus? = null
) {
    val genderType: String?
        get() = if (gender == 1) {
            "Laki-laki"
        } else if (gender == 2) {
            "Perempuan"
        } else {
            ""
        }
}

@Serializable
data class OldCreateDate(
    @SerialName("day")
    val day: String? = null,
    @SerialName("fullDate")
    val fullDate: String? = null,
    @SerialName("month")
    val month: String? = null,
    @SerialName("year")
    val year: String? = null
)

@Serializable
data class OldUserStatus(
    @SerialName("clickDate")
    val clickDate: String? = null,
    @SerialName("deviceID")
    val deviceID: String? = null,
    @SerialName("isVerified")
    val isVerified: Boolean? = null,
    @SerialName("phoneVerified")
    val phoneVerified: Boolean? = null
)
