package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.mappers

import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.interceptor.CheckVerifiedUserResInterceptor
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.response.CheckVerifiedUserResponse


fun CheckVerifiedUserResponse.toInterceptor(): CheckVerifiedUserResInterceptor {
    return CheckVerifiedUserResInterceptor(
        registered = data?.registered ?: false,
        registeredBy = data?.registeredBy ?: "",
        registeredOn = data?.registeredOn.orEmpty().filterNotNull()
    )
}
