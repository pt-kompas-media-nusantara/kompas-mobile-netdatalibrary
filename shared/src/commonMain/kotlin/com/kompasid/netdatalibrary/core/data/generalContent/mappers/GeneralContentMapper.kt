package com.kompasid.netdatalibrary.core.data.generalContent.mappers

import com.kompasid.netdatalibrary.core.data.generalContent.model.dto.GeneralContentResponse
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.AndroidRes
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.IOSRes
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.LogoRes

fun GeneralContentResponse.toInterceptor(): GeneralContentInterceptor {
    val androidLogoResList = this.result?.android?.logo?.mapNotNull { logo ->
        logo?.let {
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

    val androidRes = AndroidRes(logo = androidLogoResList)
    val iOSRes = IOSRes(logo = iOSLogoResList)

    return GeneralContentInterceptor(
        android = androidRes,
        iOS = iOSRes,
        mrwQuotaAnon = this.result?.mrwQuotaAnon ?: 3,
        mrwQuotaRegon = this.result?.mrwQuotaRegon ?: 1
    )
}