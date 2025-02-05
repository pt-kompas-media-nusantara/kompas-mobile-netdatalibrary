package com.kompasid.netdatalibrary.core.domain.generalContent.interceptor

typealias AndroidRes = PlatformRes
typealias IOSRes = PlatformRes

data class GeneralContentInterceptor(
    val android: AndroidRes,
    val iOS: IOSRes,
    val mrwQuotaAnon: Int,
    val mrwQuotaRegon: Int
)

data class PlatformRes(
    val logo: List<LogoRes>
)

data class LogoRes(
    val key: String,
    val value: String
)