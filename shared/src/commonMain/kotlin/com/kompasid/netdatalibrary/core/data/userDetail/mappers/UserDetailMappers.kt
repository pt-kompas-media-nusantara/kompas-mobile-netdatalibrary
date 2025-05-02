package com.kompasid.netdatalibrary.core.data.userDetail.mappers

import com.kompasid.netdatalibrary.core.data.userDetail.dto.response.UserDetailResponse
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserStatusInterceptor


fun UserDetailResponse.toInterceptor(): UserDetailResInterceptor {
    return UserDetailResInterceptor(
        idGender = data?.gender ?: 0,
        gender = data?.genderType ?: "",
        firstName = data?.firstName ?: "",
        lastName = data?.lastName ?: "",
        email = data?.email ?: "",
        userGuid = data?.userGuid ?: "",
        userStatus = UserStatusInterceptor(
            isVerified = data?.userStatus?.isVerified ?: false,
            phoneVerified = data?.userStatus?.phoneVerified ?: false
        ),
        phoneNumber = data?.phoneNumber ?: "",
        countryCode = data?.countryCode ?: "",
        dateBirth = data?.dateBirth ?: "",
        country = data?.country ?: "",
        province = data?.province ?: "",
        city = data?.city ?: ""
    )
}

