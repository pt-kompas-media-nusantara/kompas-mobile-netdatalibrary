package com.kompasid.netdatalibrary.core.domain.myRubriks.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.request.SaveMyRubrikRequest
import com.kompasid.netdatalibrary.core.data.myRubriks.repository.MyRubriksRepository
import kotlinx.coroutines.coroutineScope

class MyRubriksUseCase(
    private val rubriksRepository: MyRubriksRepository
) : IMyRubriksUseCase {

    suspend fun myRubriks(): Results<Unit, NetworkError> = coroutineScope {
        runCatching {
            rubriksRepository.getMyRubriks()
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is Results.Success -> {
                        Results.Success(Unit)
                    }

                    is Results.Error -> Results.Error(result.error)
                }
            },
            onFailure = { Results.Error(NetworkError.Error(it)) }
        )
    }

    suspend fun saveMyRubriks(request: SaveMyRubrikRequest): Results<Unit, NetworkError> = coroutineScope {
        runCatching {
            rubriksRepository.saveMyRubriks(request)
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is Results.Success -> {
                        Results.Success(Unit)
                    }

                    is Results.Error -> Results.Error(result.error)
                }
            },
            onFailure = { Results.Error(NetworkError.Error(it)) }
        )
    }
}