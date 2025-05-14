package com.kompasid.netdatalibrary.core.data.otp.mappers

import com.kompasid.netdatalibrary.core.data.otp.dto.interceptor.SendOTPResInterceptor
import com.kompasid.netdatalibrary.core.data.otp.dto.response.SendOTPResponse

fun SendOTPResponse.toInterceptor(): SendOTPResInterceptor {
    return SendOTPResInterceptor(
        countdown = data?.countdown ?: 0,
        remainingOtp = data?.remainingOtp ?: 0,
    )
}

