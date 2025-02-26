package com.kompasid.netdatalibrary.core.data.generalContent.mappers

import com.kompasid.netdatalibrary.core.data.generalContent.model.dto.GeneralContentResponse
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.AndroidResponse
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.IOSRes
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.LogoRes
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.ReaderConfig

fun GeneralContentResponse.toInterceptor(): GeneralContentInterceptor {
    val androidLogoResList = this.result?.android?.logo?.map { logo ->
        logo.let {
            LogoRes(
                key = it.key.orEmpty(),
                value = it.value.orEmpty()
            )
        }
    } ?: listOf()

    val iOSLogoResList = this.result?.iOS?.logo?.map { logo ->
        LogoRes(
            key = logo.key.orEmpty(),
            value = logo.value.orEmpty()
        )
    } ?: listOf()

    val androidEPaperConfig = this.result?.android?.ePaper?.let {
        ReaderConfig(
            trialTimerMode = it.trialTimerMode,
            trialPageMode = it.trialPageMode,
            isPercentageActive = it.isPercentageActive
        )
    } ?: ReaderConfig(0, 0, false)

    val androidEBookConfig = this.result?.android?.eBook?.let {
        ReaderConfig(
            trialTimerMode = it.trialTimerMode,
            trialPageMode = it.trialPageMode,
            isPercentageActive = it.isPercentageActive
        )
    } ?: ReaderConfig(0, 0, false)

    val androidRes = AndroidResponse(
        logo = androidLogoResList,
        ePaperConfig = androidEPaperConfig,
        eBookConfig = androidEBookConfig
    )

    val iOSRes = IOSRes(logo = iOSLogoResList)

    return GeneralContentInterceptor(
        android = androidRes,
        iOS = iOSRes,
        mrwQuotaAnon = this.result?.mrwQuotaAnon ?: 3,
        mrwQuotaRegon = this.result?.mrwQuotaRegon ?: 1
    )
}