package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.mappers

import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.interceptor.CheckVerifiedUserInterceptor
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.response.CheckVerifiedUserResponse

fun CheckVerifiedUserResponse.toInterceptor(): CheckVerifiedUserInterceptor {
    return CheckVerifiedUserInterceptor(
        registered = data?.registered ?: false,
        registeredBy = data?.registeredBy ?: ""
    )
}
