package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.mappers

import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.constant.CheckVerifiedUserConstant
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckVerifiedUserResponse
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.enums.RegisteredType


fun CheckVerifiedUserResponse.toInterceptor(value: String): CheckRegisteredUsersResInterceptor {
    val registeredType: RegisteredType = when (data?.registeredBy) {
        CheckVerifiedUserConstant.EMAIL -> {
            RegisteredType.EMAIL
        }
        CheckVerifiedUserConstant.PHONE_NUMBER -> {
            RegisteredType.PHONE_NUMBER
        }
        else -> {
            RegisteredType.SSO
        }
    }

    return CheckRegisteredUsersResInterceptor(
        registeredType = registeredType,
        text = value, // email atau phone number yang di input
        registered = data?.registered ?: false,
        registeredBy = data?.registeredBy ?: "",
        registeredOn = data?.registeredOn?.filterNotNull().orEmpty()
    )
}


