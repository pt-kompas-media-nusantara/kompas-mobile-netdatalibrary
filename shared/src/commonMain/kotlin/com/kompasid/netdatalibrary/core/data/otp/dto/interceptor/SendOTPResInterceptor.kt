package com.kompasid.netdatalibrary.core.data.otp.dto.interceptor

data class SendOTPResInterceptor(
    val countdown: Int,
    val remainingOtp: Int
)