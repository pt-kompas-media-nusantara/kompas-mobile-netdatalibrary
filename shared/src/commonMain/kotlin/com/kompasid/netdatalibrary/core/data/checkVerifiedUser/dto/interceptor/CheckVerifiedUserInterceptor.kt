package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.interceptor

data class CheckVerifiedUserInterceptor(
    var registered: Boolean = false,
    var registeredBy: String = "",
    // var registeredBy: List<String>
)