package com.kompasid.netdatalibrary.base.network

object ApiConfig {
    private const val CDN_URL = "https://cdn-content.kompas.id"
    private const val KID_URL = "https://api.kompas.id"
    private const val SUBS_URL = "https://apisubs.kompas.id"
    const val GENERAL_URL = "$CDN_URL/mobile/json/generalContent.json"


    const val LOGIN_BY_EMAIL_URL = "$KID_URL/account/api/v2/login/email"
    const val LOGIN_BY_GOOGLE_URL = "$KID_URL/account/api/v2/login/google"
    const val LOGIN_BY_APPLE_URL = "$KID_URL/account/api/v2/login/apple"

    const val REGISTER_URL = "$KID_URL/account/api/v2/register"

    const val USER_DETAIL_URL = "$KID_URL/account/api/v2/users/detail"
    const val USER_MEMBERSHIP_URL = "$SUBS_URL/subscriptions"
    const val USER_HISTORY_MEMBERSHIP_URL = "$KID_URL/account/api/v2/users/membership"
    const val MY_RUBRIKS_URL = "$KID_URL/account/api/v2/users/rubrik"
    const val SAVE_MY_RUBRIKS_URL = "$KID_URL/account/api/v2/users/rubrik/add"
    const val COUNTRIES_URL = "$KID_URL/account/api/v1/countries"
    const val LOGIN_GUEST_URL = "$KID_URL/account/api/v2/login/guest"
    const val REFRESH_TOKEN_URL = "$KID_URL/account/api/v1/tokens/refresh"
    const val LOGOUT_URL = "$KID_URL/account/api/v2/logout"
    const val UPDATE_PROFILE_URL = "$KID_URL/account/api/v1/users"
    const val CHECK_VERIFIED_USER_URL = "https://api.kompas.id/account/api/v2/users/check"


    const val QNA_URL = "$CDN_URL/mobile/json/question-answer.json"
}
