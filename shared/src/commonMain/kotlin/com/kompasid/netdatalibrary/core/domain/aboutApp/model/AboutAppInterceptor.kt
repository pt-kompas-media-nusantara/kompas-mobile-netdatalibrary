package com.kompasid.netdatalibrary.core.domain.aboutApp.model


data class AboutAppInterceptor(
    val appVersion: String = "",
    val desc: String = "",
    val appInfoTitle: String = "",
    val appInfo: List<AppInfoModel> = emptyList(),
)

data class AppInfoModel(
    val key: String = "",
    val value: String = "",
)
