package com.kompasid.netdatalibrary.core.data.generalContent.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor
import com.kompasid.netdatalibrary.core.domain.generalContent.repository.IGeneralContentRepository
import com.kompasid.netdatalibrary.core.domain.generalContent.usecase.GeneralContentUseCase

// nurirppan__
// ini kayanya harus di taruh di domain yu, kalau di taruh di data terlalu jauh
class GeneralContentUseCaseImpl(val repository: IGeneralContentRepository) : GeneralContentUseCase {
    override suspend fun getGeneralData(): Results<GeneralContentInterceptor, NetworkError> {
        return repository.getGeneralData()
    }
}