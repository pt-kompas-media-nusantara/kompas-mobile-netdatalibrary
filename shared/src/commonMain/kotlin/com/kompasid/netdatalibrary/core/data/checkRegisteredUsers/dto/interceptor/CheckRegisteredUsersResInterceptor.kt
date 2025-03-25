package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor

data class CheckRegisteredUsersResInterceptor(
    var registeredType: RegisteredType = RegisteredType.EMAIL,
    var text: String = "",
    var registered: Boolean = false,
    var registeredBy: String = "",
    var registeredOn: List<String> = emptyList()
)


enum class RegisteredType {
    EMAIL,
    PHONE_NUMBER
}