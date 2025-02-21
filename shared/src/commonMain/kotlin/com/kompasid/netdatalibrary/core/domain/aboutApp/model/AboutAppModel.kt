package com.kompasid.netdatalibrary.core.domain.aboutApp.model


data class AboutAppModel(
    val appVersion: String,
    val desc: String,
    val appInfoTitle: String,
    val appInfo: List<AppInfoModel>,
)

data class AppInfoModel(
    val key: String,
    val value: String,
)
