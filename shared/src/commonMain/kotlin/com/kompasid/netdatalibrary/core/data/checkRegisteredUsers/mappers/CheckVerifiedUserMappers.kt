package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.mappers

import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.constant.CheckVerifiedUserConstant
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.constants.RegisteredTypeCode
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckRegisteredUsersData
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckVerifiedUserResponse
import com.kompasid.netdatalibrary.helpers.TextType
import com.kompasid.netdatalibrary.helpers.TextValidator


fun CheckVerifiedUserResponse.toInterceptor(value: String): CheckRegisteredUsersResInterceptor {
    val safeData = data
    return CheckRegisteredUsersResInterceptor(text = value,
        registered = safeData?.registered ?: false,
        registeredType = safeData?.let { checkRegisteredType(value, it) } ?: emptyList())
}

fun checkRegisteredType(value: String, data: CheckRegisteredUsersData): List<Int> {
    val result = mutableListOf<Int>()
    val registeredBy = data.registeredBy?.lowercase().orEmpty()

    if (registeredBy == "sso") {
        data.registeredOn.orEmpty().filterNotNull().map { it.lowercase() }.forEach {
                when (it) {
                    "google" -> result.add(RegisteredTypeCode.GOOGLE)
                    "apple" -> result.add(RegisteredTypeCode.APPLE)
                }
            }
    } else {
        when (TextValidator.detectType(value)) {
            TextType.Email -> {
                if (data.registered == true && registeredBy == CheckVerifiedUserConstant.EMAIL.lowercase()) {
                    result.add(RegisteredTypeCode.EMAIL_REGISTERED)
                } else {
                    result.add(RegisteredTypeCode.EMAIL_UNREGISTERED)
                }
            }

            TextType.PhoneNumber -> {
                if (data.registered == true && registeredBy == CheckVerifiedUserConstant.PHONE_NUMBER.lowercase()) {
                    result.add(RegisteredTypeCode.PHONE_NUMBER_REGISTERED)
                } else {
                    result.add(RegisteredTypeCode.PHONE_NUMBER_UNREGISTERED)
                }
            }

            else -> {
                result.add(RegisteredTypeCode.EMAIL_UNREGISTERED)
            }
        }
    }

    return result
}
