package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.mappers

import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckVerifiedUserResponse
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.enums.RegisteredType


fun CheckVerifiedUserResponse.toInterceptor(value: String): CheckRegisteredUsersResInterceptor {
    val registeredType = if (data?.registeredBy == "email") {
        RegisteredType.EMAIL
    } else {
        RegisteredType.PHONE_NUMBER
    }

    return CheckRegisteredUsersResInterceptor(
        registeredType = registeredType,
        text = value,
        registered = data?.registered ?: false,
        registeredBy = data?.registeredBy ?: "",
        registeredOn = data?.registeredOn?.filterNotNull().orEmpty()
    )
}

