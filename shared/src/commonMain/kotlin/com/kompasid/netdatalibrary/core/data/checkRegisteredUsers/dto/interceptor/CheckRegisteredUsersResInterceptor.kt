package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor

// EMAIL, pindah ke login by input email
// PHONE_NUMBER, pindah ke login by phone number
// GOOGLE, pindah ke ke halaman multple sso
// APPLE, pindah ke ke halaman multiple sso
data class CheckRegisteredUsersResInterceptor(
    val text: String,
    val registered: Boolean,
    val registeredType: List<Int>,
)


