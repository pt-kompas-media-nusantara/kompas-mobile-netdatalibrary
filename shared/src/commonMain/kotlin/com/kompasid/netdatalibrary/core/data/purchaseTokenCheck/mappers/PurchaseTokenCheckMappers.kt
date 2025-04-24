package com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.mappers

import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.IosRecoInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OSRecommendationResInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OsInformationRecoInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OsRecomendationRecoInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.UserInterfaceRecoInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.response.OSRecommendationResponse
import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dto.interceptor.PurchaseTokenCheckResInterceptor
import com.kompasid.netdatalibrary.core.data.purchaseTokenCheck.dto.response.PurchaseTokenCheckResponse


fun PurchaseTokenCheckResponse.toInterceptor(): PurchaseTokenCheckResInterceptor {
    return PurchaseTokenCheckResInterceptor(
        email = data?.email ?: "",
        registerBy = data?.registerBy ?: "",
    )

}