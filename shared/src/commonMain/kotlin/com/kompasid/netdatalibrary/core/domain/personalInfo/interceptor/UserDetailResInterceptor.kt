package com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor


data class UserDetailResInterceptor(
    val idGender: Int,
    val gender: String,
    val userId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val userGuid: String,
    val isActive: Boolean,
    val userStatus: UserStatus,
    val phoneNumber: String,
    val countryCode: String,
    val dateBirth: String,
    val country: String,
    val province: String,
    val city: String,
)

data class UserStatus(
    val isVerified: Boolean,
    val phoneVerified: Boolean
)