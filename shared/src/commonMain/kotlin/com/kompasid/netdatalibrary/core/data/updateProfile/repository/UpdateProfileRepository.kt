package com.kompasid.netdatalibrary.core.data.updateProfile.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.request.SaveMyRubrikRequest
import com.kompasid.netdatalibrary.core.data.myRubriks.network.MyRubriksApiService
import com.kompasid.netdatalibrary.core.data.myRubriks.repository.IMyRubriksRepository
import com.kompasid.netdatalibrary.core.data.myRubriks.resultState.MyRubriksResultState
import com.kompasid.netdatalibrary.core.data.updateProfile.dto.request.UpdateProfileRequest
import com.kompasid.netdatalibrary.core.data.updateProfile.network.UpdateProfileApiService



class UpdateProfileRepository(
    private val updateProfileApiService: UpdateProfileApiService,
) : IUpdateProfileRepository {

    override suspend fun updateProfile(request: UpdateProfileRequest): Results<Unit, NetworkError> {
        return when (val result = updateProfileApiService.updateProfile(request)) {
            is ApiResults.Success -> {
                Results.Success(Unit)
            }

            is ApiResults.Error -> Results.Error(result.error) // Jika penyimpanan gagal, return error dari save API
        }
    }
}
