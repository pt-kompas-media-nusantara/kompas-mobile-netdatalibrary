package com.kompasid.netdatalibrary.core.data.generalContent.model.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GeneralContentResponse(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("result")
    val result: Result? = null,
    @SerialName("status")
    val status: String? = null,
)

@Serializable
data class Result(
    @SerialName("android")
    val android: Android? = null,
    @SerialName("iOS")
    val iOS: IOS? = null,
    @SerialName("mrw_quota_anon")
    val mrwQuotaAnon: Int? = null,
    @SerialName("mrw_quota_regon")
    val mrwQuotaRegon: Int? = null
)

@Serializable
data class IOS(
    @SerialName("logo")
    val logo: List<Logo>? = null,
)

@Serializable
data class Logo(
    @SerialName("key")
    val key: String? = null,
    @SerialName("value")
    val value: String? = null
)

@Serializable
data class Android(
    val logo: List<Logo>,
    @SerialName("ePaper")
    val ePaper: EData,
    @SerialName("eBook")
    val eBook: EData,
) {
    @Serializable
    data class EData(
        @SerialName("trial_timer_mode")
        val trialTimerMode: Int,
        @SerialName("trial_page_mode")
        val trialPageMode: Int,
        @SerialName("is_percentage_page")
        val isPercentageActive: Boolean,
    )
}


