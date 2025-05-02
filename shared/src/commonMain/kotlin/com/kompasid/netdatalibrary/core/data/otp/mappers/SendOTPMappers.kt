package com.kompasid.netdatalibrary.core.data.otp.mappers

import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.constant.CheckVerifiedUserConstant
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckVerifiedUserResponse
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.enums.RegisteredType
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.enums.SSOType
import com.kompasid.netdatalibrary.core.data.otp.dto.interceptor.SendOTPResInterceptor
import com.kompasid.netdatalibrary.core.data.otp.dto.response.SendOTPResponse

fun SendOTPResponse.toInterceptor(): SendOTPResInterceptor {
    return SendOTPResInterceptor(
        countdown = data?.countdown ?: 0,
        remainingOtp = data?.remainingOtp ?: 0,
    )
}