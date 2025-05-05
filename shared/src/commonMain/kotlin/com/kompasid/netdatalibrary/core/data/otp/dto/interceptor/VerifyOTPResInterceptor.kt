package com.kompasid.netdatalibrary.core.data.otp.dto.interceptor


data class VerifyOTPResInterceptor(
    val accessToken: String,
    val refreshToken: String,
    val deviceKeyId: String,
    val isSocial: Boolean,
    val isPassEmpty: Boolean,
)