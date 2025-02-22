package com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor


data class UserDetailResInterceptor(
    val idGender: Int = 0,
    val gender: String = "",
    val userId: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val userGuid: String = "",
    val isActive: Boolean = false,
    val userStatus: UserStatusInterceptor = UserStatusInterceptor(false, false),
    val phoneNumber: String = "",
    val countryCode: String = "",
    val dateBirth: String = "",
    val country: String = "",
    val province: String = "",
    val city: String = "",
)

data class UserStatusInterceptor(
    val isVerified: Boolean = false,
    val phoneVerified: Boolean = false
)