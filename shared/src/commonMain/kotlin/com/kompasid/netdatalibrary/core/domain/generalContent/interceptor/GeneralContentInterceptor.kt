package com.kompasid.netdatalibrary.core.domain.generalContent.interceptor

import com.kompasid.netdatalibrary.core.data.generalContent.model.dto.Android.EData

typealias IOSRes = PlatformRes

data class GeneralContentInterceptor(
    val android: AndroidResponse,
    val iOS: IOSRes,
    val mrwQuotaAnon: Int,
    val mrwQuotaRegon: Int
)

data class PlatformRes(
    val logo: List<LogoRes>
)

data class AndroidResponse(
    val logo: List<LogoRes>,
    val ePaperConfig: ReaderConfig,
    val eBookConfig: ReaderConfig
)

data class ReaderConfig(
    val trialTimerMode: Int,
    val trialPageMode: Int,
    val isPercentageActive: Boolean
) {
    private var pageCount: Int = 0

    val isTrialTimerActive: Boolean get() = trialTimerMode > 0

    val isTrialPageActive: Boolean get() = trialPageMode > 0

    val limitPageIndex: Int
        get() = if (isPercentageActive) {
            ((pageCount * trialPageMode) / 100) - 1
        } else {
            trialPageMode - 1
        }.coerceAtLeast(0)

    val limitPageText: String get() = (limitPageIndex + 1).toString()

    fun setPageCount(pageCount: Int) = apply {
        this.pageCount = pageCount
    }

    companion object {
        fun init(): EData {
            return EData(
                trialTimerMode = 0,
                trialPageMode = 0,
                isPercentageActive = false,
            )
        }
    }
}

data class LogoRes(
    val key: String,
    val value: String
)