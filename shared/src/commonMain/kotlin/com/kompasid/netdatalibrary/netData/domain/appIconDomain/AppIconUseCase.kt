package com.kompasid.netdatalibrary.netData.domain.appIconDomain

import com.kompasid.netdatalibrary.netData.data.appIconData.AppIconRepository
import com.kompasid.netdatalibrary.base.interactor.NetworkError
import com.kompasid.netdatalibrary.base.interactor.Results


class AppIconUseCase(
    private val appIconRepository: AppIconRepository
) {

    suspend fun appIcon(): Results<AppIconResInterceptor, NetworkError> {
        when (val response = appIconRepository.getAppIcon()) {
            is Results.Success -> {
                val appIconResponse = response.data

                // Konversi dari Logo ke LogoRes untuk Android
                val androidLogoResList = appIconResponse.result?.android?.logo?.mapNotNull { logo ->
                    logo?.let {
                        LogoRes(
                            key = it.key.orEmpty(),
                            value = it.value.orEmpty()
                        )
                    }
                } ?: listOf()

                // Konversi dari Logo ke LogoRes untuk iOS
                val iOSLogoResList = appIconResponse.result?.iOS?.logo?.map { logo ->
                    LogoRes(
                        key = logo.key.orEmpty(),
                        value = logo.value.orEmpty()
                    )
                } ?: listOf()

                // Buat objek AndroidRes dan IOSRes
                val androidRes = AndroidRes(logo = androidLogoResList)
                val iOSRes = IOSRes(logo = iOSLogoResList)

                // Buat objek AppIconResInterceptor
                val appIconResInterceptor = AppIconResInterceptor(
                    android = androidRes,
                    iOS = iOSRes,
                    mrwQuotaAnon = appIconResponse.result?.mrwQuotaAnon ?: 3,
                    mrwQuotaRegon = appIconResponse.result?.mrwQuotaRegon ?: 1
                )

                return Results.Success(appIconResInterceptor)
            }

            is Results.Error -> {
                return Results.Error(response.error)
            }
        }
    }


}