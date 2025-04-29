package com.kompasid.netdatalibrary.base.network.ApiEnv

object ApiConfig {
    private const val CDN_URL = "https://cdn-content.kompas.id"
    private const val KID_URL = "https://api.kompas.id"
    private const val KID_CLOUD_URL = "https://api.kompas.cloud"
    private const val SUBS_URL = "https://apisubs.kompas.id"
    private const val APIARY_URL = "https://private-d6360b-hariankompasios.apiary-mock.com"


    const val GENERAL_URL = "$CDN_URL/mobile/json/generalContent.json"


    const val LOGIN_BY_EMAIL_URL_PROD = "$KID_URL/account/api/v2/login/email"
    const val LOGIN_BY_EMAIL_URL_DEV = "$KID_CLOUD_URL/account/api/v2/login/email"

    const val LOGIN_BY_GOOGLE_URL_PROD = "$KID_URL/account/api/v2/login/google"
    const val LOGIN_BY_GOOGLE_URL_DEV = "$KID_CLOUD_URL/account/api/v2/login/google"

    const val LOGIN_BY_APPLE_URL_PROD = "$KID_URL/account/api/v2/login/apple"
    const val LOGIN_BY_APPLE_URL_DEV = "$KID_CLOUD_URL/account/api/v2/login/apple"

    const val LOGIN_BY_PURCHASE_TOKEN_URL_PROD = "$KID_URL/account/api/v2/login/purchase-token"
    const val LOGIN_BY_PURCHASE_TOKEN_URL_DEV = "$KID_CLOUD_URL/account/api/v2/login/purchase-token"

    const val REGISTER_URL_PROD = "$KID_URL/account/api/v2/register"
    const val REGISTER_URL_DEV = "$KID_CLOUD_URL/account/api/v2/register"

    const val LOGOUT_URL_PROD = "$KID_URL/account/api/v2/logout"
    const val LOGOUT_URL_DEV = "$KID_CLOUD_URL/account/api/v2/logout"

    const val USER_DETAIL_URL_PROD = "$KID_URL/account/api/v2/users/detail"
    const val USER_DETAIL_URL_DEV = "$KID_CLOUD_URL/account/api/v2/users/detail"

    const val USER_MEMBERSHIP_URL_PROD = "$SUBS_URL/subscriptions"
    const val USER_MEMBERSHIP_URL_DEV = "$SUBS_URL/subscriptions"

    const val USER_HISTORY_MEMBERSHIP_URL_PROD = "$KID_URL/account/api/v2/users/membership"
    const val USER_HISTORY_MEMBERSHIP_URL_DEV = "$KID_CLOUD_URL/account/api/v2/users/membership"

    const val MY_RUBRIKS_URL = "$KID_URL/account/api/v2/users/rubrik"
    const val SAVE_MY_RUBRIKS_URL = "$KID_URL/account/api/v2/users/rubrik/add"
    const val COUNTRIES_URL = "$KID_URL/account/api/v1/countries"
    const val LOGIN_GUEST_URL = "$KID_URL/account/api/v2/login/guest"
    const val REFRESH_TOKEN_URL = "$KID_URL/account/api/v1/tokens/refresh"
    const val UPDATE_PROFILE_URL = "$KID_URL/account/api/v1/users"

    const val CHECK_VERIFIED_USER_URL_PROD = "$KID_URL/account/api/v2/users/check"
    const val CHECK_VERIFIED_USER_URL_DEV = "$KID_CLOUD_URL/account/api/v2/users/check"


    const val QNA_URL = "$CDN_URL/mobile/json/question-answer.json"

    const val OS_RECOMMENDATION_PROD = "$CDN_URL/mobile/json/recommendedOSSettings.json"
    const val OS_RECOMMENDATION_DEV = "$APIARY_URL/recommendos"

    const val FORCE_UPDATE_PROD = "$CDN_URL/mobile/json/iosForceUpdate.json"
    const val FORCE_UPDATE_DEV = "$APIARY_URL/iosForceUpdate"

    const val PURCHASE_TOKEN_CHECK_PROD = "$SUBS_URL/purchase-token/user-check"
    const val PURCHASE_TOKEN_CHECK_DEV = "$SUBS_URL/purchase-token/user-check"
    const val PURCHASE_TOKEN_CHECK_STAG = "$KID_CLOUD_URL/subscription/api/v1/purchase-token/user-check"
}
