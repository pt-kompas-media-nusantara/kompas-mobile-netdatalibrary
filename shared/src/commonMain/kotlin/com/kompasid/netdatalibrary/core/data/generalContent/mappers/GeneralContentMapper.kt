package com.kompasid.netdatalibrary.core.data.generalContent.mappers

import com.kompasid.netdatalibrary.core.data.generalContent.model.dto.GeneralContentResponse
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.AndroidRes
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.IOSRes
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.LogoRes

// nurirppan__
// ini penggunaan extension ya ? bagus sih bisa langsung panggil result.data.toInterceptor() dan hanya
// bisa dipanggil di GeneralContentResponse. selama responsenya terisolasi dengan api yang lain harusnya ini aman ya
// maksudnya responsenya jangan di pakai dengan api yang lain

// TODO: Wahyu - Betul ini khusus untuk response general content aja mas
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