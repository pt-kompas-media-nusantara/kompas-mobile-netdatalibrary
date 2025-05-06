package com.kompasid.netdatalibrary.core.domain.account.enums

import com.kompasid.netdatalibrary.core.domain.account.model.NavigationType

enum class AccountNavigationType(val value: String, navigationType: NavigationType) {
    NEXT("NEXT", NavigationType.NATIVE),
    AUTO_LOGIN("AUTO_LOGIN", NavigationType.NATIVE),
    APULO("APULO", NavigationType.NATIVE),
    LOGIN("LOGIN", NavigationType.NATIVE),
    REGISTER("REGISTER", NavigationType.NATIVE),
    REGISTER_WALL("REGISTER_WALL", NavigationType.NATIVE),
    MANAGE_ACCOUNT("MANAGE_ACCOUNT", NavigationType.NATIVE),
    SUBSCRIPTION("SUBSCRIPTION", NavigationType.NATIVE),
    BOOKMARK("BOOKMARK", NavigationType.NATIVE),
    REWARD("REWARD", NavigationType.NATIVE),
    SETTINGS("SETTINGS", NavigationType.NATIVE),
    CONTACT_US("CONTACT_US", NavigationType.NATIVE),
    QNA("QNA", NavigationType.NATIVE),
    ABOUT_APP("ABOUT_APP", NavigationType.WEBVIEW("https://reward.kompas.id/")),
    ABOUT_HARIAN_KOMPAS("ABOUT_HARIAN_KOMPAS", NavigationType.NATIVE),

    /// Tentang Harian Kompas
    COMPANY_PROFILE("COMPANY_PROFILE", NavigationType.WEBVIEW("https://korporasi.kompas.id/")),
    COMPANY_HISTORY("COMPANY_HISTORY",
        NavigationType.WEBVIEW("https://korporasi.kompas.id/profil/cerita-berdiri/")
    ),
    ABOUT_ORGANIZATION(
        "ABOUT_ORGANIZATION",
        NavigationType.WEBVIEW("https://www.kompas.id/organisasi/")
    ),

    /// Tentang Aplikasi
    ABOUT_APP_SUBMENU("ABOUT_APP_SUBMENU", NavigationType.NATIVE),
    TERMS_CONDITIONS("TERMS_CONDITIONS",
        NavigationType.WEBVIEW("https://www.kompas.id/syarat-dan-ketentuan/")
    ),
    CYBER_MEDIA_GUIDELINES("CYBER_MEDIA_GUIDELINES",
        NavigationType.WEBVIEW("https://www.kompas.id/pedoman-media-siber/")
    ),

    THEME("THEME", NavigationType.NATIVE),
    CHANGE_PASSWORD("CHANGE_PASSWORD",
        NavigationType.WEBVIEW("https://account.kompas.id/reset-password/send-link")
    ),
    DELETE_DATA("DELETE_DATA", NavigationType.ACTION_NATIVE),
    DEVICE_ACTIVITIES("DEVICE_ACTIVITIES", NavigationType.NATIVE),
    DELETE_ACCOUNT("DELETE_ACCOUNT", NavigationType.NATIVE),
    SIGN_OUT("DELETE_ACCOUNT", NavigationType.ACTION_NATIVE),
}

