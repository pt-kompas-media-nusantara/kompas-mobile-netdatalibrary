package com.kompasid.netdatalibrary.core.domain.generalContent.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor
import com.kompasid.netdatalibrary.core.data.generalContent.repository.IGeneralContentRepository

class GeneralContentUseCase(private val repository: IGeneralContentRepository) : IGeneralContentUseCase {
    override suspend fun getGeneralData(customUrl: String): Results<GeneralContentInterceptor, NetworkError> {
        return repository.getGeneralData(customUrl)
    }
}