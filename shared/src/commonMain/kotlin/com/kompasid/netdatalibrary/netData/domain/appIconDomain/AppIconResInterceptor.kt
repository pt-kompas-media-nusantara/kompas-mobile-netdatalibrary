package com.kompasid.netdatalibrary.netData.domain.appIconDomain


data class AppIconResInterceptor(
    val android: AndroidRes,
    val iOS: IOSRes,
    val mrwQuotaAnon: Int,
    val mrwQuotaRegon: Int
)


data class AndroidRes(
    val logo: List<LogoRes>
)

data class IOSRes(
    val logo: List<LogoRes>
)

data class LogoRes(
    val key: String,
    val value: String
)