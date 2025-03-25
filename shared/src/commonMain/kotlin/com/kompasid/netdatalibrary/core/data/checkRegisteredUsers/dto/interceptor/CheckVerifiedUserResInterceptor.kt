package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor

data class CheckVerifiedUserResInterceptor(
    var registered: Boolean = false,
    var registeredBy: String = "",
    var registeredOn: List<String> = emptyList()
)