package com.kompasid.netdatalibrary.core.domain.generalContent.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor
import com.kompasid.netdatalibrary.core.data.generalContent.repository.IGeneralContentRepository

class GeneralContentUseCase(val repository: IGeneralContentRepository) : IGeneralContentUseCase {
    override suspend fun getGeneralData(): Results<GeneralContentInterceptor, NetworkError> {
        return repository.getGeneralData()
    }
}