package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.interceptor.CheckVerifiedUserInterceptor
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.request.CheckVerifiedUserRequest

interface ICheckVerifiedUserRepository {
    suspend fun postCheckVerifiedUser(request: CheckVerifiedUserRequest): Results<CheckVerifiedUserInterceptor, NetworkError>
}