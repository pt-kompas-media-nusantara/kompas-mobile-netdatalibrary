package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.enums

enum class RegisteredType {
    EMAIL, // kalau ini akan pindah ke halaman input password
    PHONE_NUMBER, // kalau ini akan menampilkan bottom sheet phone number otp whatsapp
    SSO // kalau ini akan menampilkan halaman multiple sso
}