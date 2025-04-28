package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor

import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.enums.RegisteredType

data class CheckRegisteredUsersResInterceptor(
    var registeredType: RegisteredType,
    var text: String,
    var registered: Boolean,
    var registeredBy: String,
    var registeredOn: List<String>
)


