package com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map


data class UserDetailResInterceptor(
    var idGender: Int,
    var gender: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var userGuid: String,
    var userStatus: UserStatusInterceptor,
    var phoneNumber: String,
    var countryCode: String,
    var dateBirth: String,
    var country: String,
    var province: String,
    var city: String,
)

data class UserStatusInterceptor(
    var isVerified: Boolean,
    var phoneVerified: Boolean
)

