package com.kompasid.netdatalibrary.core.data.register.mappers

import com.kompasid.netdatalibrary.core.data.register.dto.interceptor.RegisterResInterceptor
import com.kompasid.netdatalibrary.core.data.register.dto.response.RegisterResponse
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.response.OldUserDetailResponse


fun RegisterResponse.toInterceptor(): RegisterResInterceptor {
    return RegisterResInterceptor(
        accessToken = data?.accessToken ?: "",
        refreshToken = data?.refreshToken ?: "",
        registeredOn = data?.registeredOn?.filterNotNull().orEmpty()
    )
}