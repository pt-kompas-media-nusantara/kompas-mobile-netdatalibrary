package com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor


data class UserDetailResInterceptor(
    var idGender: Int = 0,
    var gender: String = "",
    var userId: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var userGuid: String = "",
    var isActive: Boolean = false,
    var userStatus: UserStatusInterceptor = UserStatusInterceptor(false, false),
    var phoneNumber: String = "",
    var countryCode: String = "",
    var dateBirth: String = "",
    var country: String = "",
    var province: String = "",
    var city: String = "",
)

data class UserStatusInterceptor(
    var isVerified: Boolean = false,
    var phoneVerified: Boolean = false
)