package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.mappers

import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.constant.CheckVerifiedUserConstant
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.RegisteredTypeCode
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckVerifiedUserResponse


fun CheckVerifiedUserResponse.toInterceptor(value: String): CheckRegisteredUsersResInterceptor {
    return CheckRegisteredUsersResInterceptor(
        text = value,
        registered = data?.registered ?: false,
        registeredType = checkRegisteredType(data?.registeredBy, data?.registeredOn)
    )
}

fun checkRegisteredType(registeredBy: String?, registeredOn: List<String?>?): List<Int> {
    val result = mutableListOf<Int>()

    when (registeredBy?.lowercase()) {
        CheckVerifiedUserConstant.EMAIL.lowercase() -> result.add(RegisteredTypeCode.EMAIL)
        CheckVerifiedUserConstant.PHONE_NUMBER.lowercase() -> result.add(RegisteredTypeCode.PHONE_NUMBER)
        else -> {
            val onList = registeredOn.orEmpty().filterNotNull().map { it.lowercase() }
            if ("google" in onList) result.add(RegisteredTypeCode.GOOGLE)
            if ("apple" in onList) result.add(RegisteredTypeCode.APPLE)
        }
    }

    return result
}


