package com.kompasid.netdatalibrary.core.data.generalContent.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.generalContent.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.generalContent.network.GeneralContentApiService
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor
import com.kompasid.netdatalibrary.core.domain.generalContent.repository.IGeneralContentRepository

class GeneralContentRepository(
    private val generalContentServices: GeneralContentApiService
) : IGeneralContentRepository {

    override suspend fun getGeneralData(): Results<GeneralContentInterceptor, NetworkError> {
        return when (val result = generalContentServices.getGeneralContent()) {
            is ApiResults.Success -> {
                Results.Success(result.data.toInterceptor())
            }

            is ApiResults.Error -> {
                Results.Error(result.error)
            }
        }
    }
}