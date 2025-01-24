package com.kompasid.netdatalibrary.core.data.userDetailData.dto

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class UserDetailResponse(
    @SerialName("address")
    val address: Address? = null,
    @SerialName("authPriority")
    val authPriority: AuthPriority? = null,
    @SerialName("authentication")
    val authentication: Authentication? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("countryCode")
    val countryCode: String? = null,
    @SerialName("createDate")
    val createDate: CreateDate? = null,
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
    val userStatus: UserStatus? = null
)

@Serializable
data class Address(
    @SerialName("addressBillCompleted")
    val addressBillCompleted: Boolean? = null,
    @SerialName("addressBilling")
    val addressBilling: AddressBilling? = null,
    @SerialName("addressShipCompleted")
    val addressShipCompleted: Boolean? = null,
    @SerialName("addressShipment")
    val addressShipment: AddressShipment? = null
)

@Serializable
data class AuthPriority(
//    @SerialName("alternate")
//    val alternate: List<Any?>? = null,
    @SerialName("main")
    val main: List<String>? = null
)

@Serializable
data class Authentication(
    @SerialName("apple")
    val apple: Apple? = null,
    @SerialName("facebook")
    val facebook: Facebook? = null,
    @SerialName("google")
    val google: Google? = null
)

@Serializable
data class CreateDate(
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
data class UserStatus(
    @SerialName("clickDate")
    val clickDate: String? = null,
    @SerialName("deviceID")
    val deviceID: String? = null,
    @SerialName("isVerified")
    val isVerified: Boolean? = null,
    @SerialName("phoneVerified")
    val phoneVerified: Boolean? = null
)

@Serializable
data class AddressBilling(
    @SerialName("address")
    val address: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("companyName")
    val companyName: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("district")
    val district: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("firstName")
    val firstName: String? = null,
    @SerialName("lastName")
    val lastName: String? = null,
    @SerialName("phoneNumber")
    val phoneNumber: String? = null,
    @SerialName("postalCode")
    val postalCode: String? = null,
    @SerialName("province")
    val province: String? = null,
    @SerialName("village")
    val village: String? = null
)

@Serializable
data class AddressShipment(
    @SerialName("address")
    val address: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("companyName")
    val companyName: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("district")
    val district: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("firstName")
    val firstName: String? = null,
    @SerialName("lastName")
    val lastName: String? = null,
    @SerialName("phoneNumber")
    val phoneNumber: String? = null,
    @SerialName("postalCode")
    val postalCode: String? = null,
    @SerialName("province")
    val province: String? = null,
    @SerialName("village")
    val village: String? = null
)

@Serializable
data class Apple(
    @SerialName("email")
    val email: String? = null,
    @SerialName("token")
    val token: String? = null
)

@Serializable
data class Facebook(
    @SerialName("email")
    val email: String? = null
)

@Serializable
data class Google(
    @SerialName("email")
    val email: String? = null
)


