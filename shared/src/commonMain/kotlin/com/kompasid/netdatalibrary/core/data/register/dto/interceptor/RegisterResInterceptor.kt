package com.kompasid.netdatalibrary.core.data.register.dto.interceptor

data class RegisterResInterceptor(
    var accessToken: String = "",
    var refreshToken: String = "",
    var registeredOn: List<String> = emptyList()
)