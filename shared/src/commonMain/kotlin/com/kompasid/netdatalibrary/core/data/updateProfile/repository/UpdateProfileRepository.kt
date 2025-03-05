package com.kompasid.netdatalibrary.core.data.updateProfile.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.updateProfile.dto.request.UpdateProfileRequest
import com.kompasid.netdatalibrary.core.data.updateProfile.network.UpdateProfileApiService


class UpdateProfileRepository(
    private val updateProfileApiService: UpdateProfileApiService,
) : IUpdateProfileRepository {

    override suspend fun updateProfile(request: UpdateProfileRequest): Results<Unit, NetworkError> =
        runCatching {
            updateProfileApiService.updateProfile(request)
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is ApiResults.Success -> Results.Success(Unit)
                    is ApiResults.Error -> Results.Error(result.error)
                }
            },
            onFailure = { exception ->
                Results.Error(NetworkError.Error(exception))
            }
        )
}
