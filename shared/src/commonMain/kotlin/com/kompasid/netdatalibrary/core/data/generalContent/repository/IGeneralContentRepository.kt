package com.kompasid.netdatalibrary.core.data.generalContent.repository

import com.kompasid.netdatalibrary.base.network.ApiEnv.ApiConfig
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor

interface IGeneralContentRepository {
    suspend fun getGeneralData(customUrl: String = ApiConfig.GENERAL_URL) : Results<GeneralContentInterceptor, NetworkError>
}