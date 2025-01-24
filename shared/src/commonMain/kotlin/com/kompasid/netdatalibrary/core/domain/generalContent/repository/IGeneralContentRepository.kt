package com.kompasid.netdatalibrary.core.domain.generalContent.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor

interface IGeneralContentRepository {
    suspend fun getGeneralData() : Results<GeneralContentInterceptor, NetworkError>
}