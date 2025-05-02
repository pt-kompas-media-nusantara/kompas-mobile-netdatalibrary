package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.mappers

import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.constant.CheckVerifiedUserConstant
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckVerifiedUserResponse
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.enums.RegisteredType
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.enums.SSOType


fun CheckVerifiedUserResponse.toInterceptor(value: String): CheckRegisteredUsersResInterceptor {
    val registeredOn = data?.registeredOn.orEmpty().filterNotNull().map { it.lowercase() }

    val registeredType: RegisteredType = when (data?.registeredBy?.lowercase()) {
        CheckVerifiedUserConstant.EMAIL.lowercase() -> RegisteredType.EMAIL
        CheckVerifiedUserConstant.PHONE_NUMBER.lowercase() -> RegisteredType.PHONE_NUMBER
        else -> {
            val ssoTypes = mutableListOf<SSOType>()
            if ("google" in registeredOn) ssoTypes.add(SSOType.GOOGLE)
            if ("apple" in registeredOn) ssoTypes.add(SSOType.APPLE)
            RegisteredType.SSO(ssoTypes)
        }
    }

    return CheckRegisteredUsersResInterceptor(
        registeredType = registeredType,
        text = value,
        registered = data?.registered ?: false,
        registeredOn = data?.registeredOn?.filterNotNull().orEmpty()
    )
}



