package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor

import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.enums.RegisteredType

data class CheckRegisteredUsersResInterceptor(
    var registeredType: RegisteredType = RegisteredType.EMAIL,
    var text: String = "",
    var registered: Boolean = false,
    var registeredBy: String = "",
    var registeredOn: List<String> = emptyList()
)


