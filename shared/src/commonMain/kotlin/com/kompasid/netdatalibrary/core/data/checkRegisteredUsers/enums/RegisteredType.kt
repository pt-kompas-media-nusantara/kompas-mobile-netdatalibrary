package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.enums

//enum class RegisteredType {
//    EMAIL, // kalau ini akan pindah ke halaman input password
//    PHONE_NUMBER, // kalau ini akan menampilkan bottom sheet phone number otp whatsapp
//    SSO(SSOType) // kalau ini akan menampilkan halaman multiple sso | var registeredOn: List<String>
//}
//
//enum class SSOType {
//    APPLE,
//    GOOGLE
//}

sealed class RegisteredType {
    object EMAIL : RegisteredType() // kalau ini akan pindah ke halaman input password
    object PHONE_NUMBER : RegisteredType() // kalau ini akan menampilkan bottom sheet phone number otp whatsapp
    data class SSO(val type: List<SSOType>) : RegisteredType()  // kalau ini akan menampilkan halaman multiple sso
}

enum class SSOType {
    APPLE,
    GOOGLE
}
