package com.kompasid.netdatalibrary.core.domain.generalContent.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor

// nurirppan__
// ini kejauhan yu, carinya nanti susah harus scrool atas bawah. better di jadiin 1 folder aja di taruh di data
interface IGeneralContentRepository {
    suspend fun getGeneralData() : Results<GeneralContentInterceptor, NetworkError>
}