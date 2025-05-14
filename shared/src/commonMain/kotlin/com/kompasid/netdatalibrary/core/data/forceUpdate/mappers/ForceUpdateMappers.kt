package com.kompasid.netdatalibrary.core.data.forceUpdate.mappers

import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.interceptor.ForceUpdateResInterceptor
import com.kompasid.netdatalibrary.core.data.forceUpdate.dto.response.ForceUpdateResponse


fun ForceUpdateResponse.toInterceptor(): ForceUpdateResInterceptor {
    return ForceUpdateResInterceptor(
        maxVersion = maxVersion ?: "",
        minVersion = minVersion ?: "",
        mobileVersion = mobileVersion ?: ""
    )

}