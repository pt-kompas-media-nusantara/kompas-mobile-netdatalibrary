package com.kompasid.netdatalibrary.core.data.updateProfile.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.updateProfile.dto.request.UpdateProfileRequest
import com.kompasid.netdatalibrary.core.data.updateProfile.dto.response.UpdateProfileResponse

interface IUpdateProfileApiService {
    suspend fun updateProfile(request: UpdateProfileRequest): ApiResults<UpdateProfileResponse, NetworkError>
}