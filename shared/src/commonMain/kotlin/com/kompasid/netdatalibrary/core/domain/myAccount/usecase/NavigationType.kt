package com.kompasid.netdatalibrary.netData.domain.MyAccountDomain

sealed class NavigationType {
    object ACTION_NATIVE : NavigationType()
    object NATIVE : NavigationType()
    data class WEBVIEW(val url: String) : NavigationType()
}