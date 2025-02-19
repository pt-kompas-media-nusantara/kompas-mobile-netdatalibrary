package com.kompasid.netdatalibrary.core.domain.account.model

sealed class NavigationType {
    object ACTION_NATIVE : NavigationType()
    object NATIVE : NavigationType()
    data class WEBVIEW(val url: String) : NavigationType()
}