package com.kompasid.netdatalibrary.netData.domain.trackerDomain.enums

enum class AuthenticationEntryPoint(val value: String) {
    SIDENAV("Sidenav"),
    ARTICLE_DETAIL("Article Detail Page"),
    BOOKMARK("Bookmark"),
    NEWS_AUDIO("News Audio"),
    PILIHANKU("Pilihanku"),
    DARK_MODE("Dark Mode"),
    ACCOUNT("Akun Page"),
    EPAPER("ePaper Detail Page"),
    EBOOK("eBook Detail Page"),
    ONBOARDING("Onboarding"),
    SUBSCRIPTION_BANNER("Subscription Banner Between Article"),
    COMMENT("Comment"),
    DASHBOARD_PAGE("Akun Saya Page"),
    BOOKMARK_PAGE("Baca Nanti Page"),
    SETTINGS_PAGE("Pengaturan Page"),
    NOTIFICATION_PAGE("Notifikasi Page"),
    AUTO_LOGIN_SIGN_UP("Auto Login Page"),
    AUTO_LOGIN_SIGN_IN_BY_PURCHASE_TOKEN("Auto Login"),
    AUTO_LOGIN_SIGN_IN_BY_OTHER_METHODS("Auto Login Another Account");

    companion object {
        fun fromValue(value: String): AuthenticationEntryPoint {
            return values().find { it.value == value }
                ?: SUBSCRIPTION_BANNER // Default case jika tidak ditemukan
        }
    }
}

enum class UserType(val value: String) {
    SUBSCRIBER("S"),
    REGISTER("R"),
    ANONYMOUS("G"),
}

enum class StateSubscriptionType(val value: String) {
    ACTIVE("AC"),
    IN_ACTIVE("IA"),
}
