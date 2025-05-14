package com.kompasid.netdatalibrary.core.data.otp.mappers

import com.kompasid.netdatalibrary.core.data.otp.dto.interceptor.VerifyOTPResInterceptor
import com.kompasid.netdatalibrary.core.data.otp.dto.response.VerifyOTPResponse

fun VerifyOTPResponse.toInterceptor(): VerifyOTPResInterceptor {
    return VerifyOTPResInterceptor(
        accessToken = data?.accessToken ?: "",
        refreshToken = data?.refreshToken ?: "",
        deviceKeyId = data?.deviceKeyId ?: "",
        isSocial = data?.isSocial ?: false,
        isPassEmpty = data?.isPassEmpty ?: false,
    )
}