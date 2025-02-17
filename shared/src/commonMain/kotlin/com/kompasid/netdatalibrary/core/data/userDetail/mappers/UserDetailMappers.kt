package com.kompasid.netdatalibrary.core.data.mappers

import com.kompasid.netdatalibrary.core.data.userDetail.dto.response.OldUserDetailResponse
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserStatusInterceptor


fun OldUserDetailResponse.toInterceptor(): UserDetailResInterceptor {
    return UserDetailResInterceptor(
        idGender = gender ?: 0,
        gender = genderType ?: "",
        userId = userId ?: "",
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        email = email ?: "",
        userGuid = userGuid ?: "",
        isActive = isActive ?: false,
        userStatus = UserStatusInterceptor(
            isVerified = userStatus?.isVerified ?: false,
            phoneVerified = userStatus?.phoneVerified ?: false
        ),
        phoneNumber = phoneNumber ?: "",
        countryCode = countryCode ?: "",
        dateBirth = dateBirth ?: "",
        country = country ?: "",
        province = province ?: "",
        city = city ?: ""
    )
}

