package com.kompasid.netdatalibrary.core.data.osRecomendation.mappers

import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.IosRecoInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OSRecommendationResInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OsInformationRecoInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.OsRecomendationRecoInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.interceptor.UserInterfaceRecoInterceptor
import com.kompasid.netdatalibrary.core.data.osRecomendation.dto.response.OSRecommendationResponse

fun OSRecommendationResponse.toInterceptor(): OSRecommendationResInterceptor {
    return OSRecommendationResInterceptor(
        ios = IosRecoInterceptor(
            minimumOS = ios?.minimumOS ?: "",
            osRecommendation = ios?.osRecommendation ?: "",
            osVersion = ios?.osVersion ?: "",
            userInterface = UserInterfaceRecoInterceptor(
                osInformation = OsInformationRecoInterceptor(
                    descriptiion = ios?.userInterface?.osInformation?.descriptiion ?: "",
                    title = ios?.userInterface?.osInformation?.title ?: "",
                ),
                osRecomendation = OsRecomendationRecoInterceptor(
                    descriptiion = ios?.userInterface?.osRecomendation?.descriptiion ?: "",
                    title = ios?.userInterface?.osRecomendation?.descriptiion ?: "",
                )
            ),
        )
    )

}