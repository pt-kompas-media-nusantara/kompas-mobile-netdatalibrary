package com.kompasid.netdatalibrary.core.data.updateProfile.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.updateProfile.dto.request.UpdateProfileRequest

interface IUpdateProfileRepository {
    suspend fun updateProfile(request: UpdateProfileRequest): Results<Unit, NetworkError>
}