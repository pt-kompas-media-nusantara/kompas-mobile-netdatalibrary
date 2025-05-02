package com.kompasid.netdatalibrary.core.data.register.mappers

import com.kompasid.netdatalibrary.core.data.register.dto.interceptor.RegisterResInterceptor
import com.kompasid.netdatalibrary.core.data.register.dto.response.RegisterResponse


fun RegisterResponse.toInterceptor(): RegisterResInterceptor {
    return RegisterResInterceptor(
        accessToken = data?.accessToken ?: "",
        refreshToken = data?.refreshToken ?: "",
        registeredOn = data?.registeredOn?.filterNotNull().orEmpty()
    )
}