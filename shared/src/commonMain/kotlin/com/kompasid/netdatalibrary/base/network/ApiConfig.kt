package com.kompasid.netdatalibrary.base.network

object ApiConfig {
    private const val CDN_URL = "https://cdn-content.kompas.id"
    private const val KID_URL = "https://api.kompas.id"
    const val GENERAL_URL = "$CDN_URL/mobile/json/generalContent.json"
    const val USER_DETAIL_URL = "$KID_URL/account/api/v2/users/detail"
    const val USER_HISTORY_MEMBERSHIP_URL = "$KID_URL/account/api/v2/users/membership"
    const val MY_RUBRIKS_URL = "$KID_URL/account/api/v2/users/rubrik"
}
