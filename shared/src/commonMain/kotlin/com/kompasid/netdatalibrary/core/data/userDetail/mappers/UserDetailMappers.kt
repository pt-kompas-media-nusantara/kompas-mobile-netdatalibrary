package com.kompasid.netdatalibrary.core.data.mappers

import com.kompasid.netdatalibrary.core.data.userDetailData.dto.OldUserDetailResponse
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserStatus


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
        userStatus = UserStatus(
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

