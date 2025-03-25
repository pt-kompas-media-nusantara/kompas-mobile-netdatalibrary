package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.mappers

import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckVerifiedUserResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.response.CheckVerifiedUserResponse


fun CheckVerifiedUserResponse.toInterceptor(): CheckVerifiedUserResInterceptor {
    return CheckVerifiedUserResInterceptor(
        registered = data?.registered ?: false,
        registeredBy = data?.registeredBy ?: "",
        registeredOn = data?.registeredOn.orEmpty().filterNotNull()
    )
}
