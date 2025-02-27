package com.kompasid.netdatalibrary.core.data.checkVerifiedUser.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.interceptor.CheckVerifiedUserInterceptor
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dto.request.CheckVerifiedUserRequest
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.network.CheckVerifiedUserApiService
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.dataSource.CheckVerifiedUserDataSource
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.mappers.toInterceptor


class CheckVerifiedUserRepository(
    private val checkVerifiedUserApiService: CheckVerifiedUserApiService,
    private val checkVerifiedUserDataSource: CheckVerifiedUserDataSource
) : ICheckVerifiedUserRepository {

    override suspend fun postCheckVerifiedUser(request: CheckVerifiedUserRequest): Results<CheckVerifiedUserInterceptor, NetworkError> {
        return when (val result = checkVerifiedUserApiService.postCheckVerifiedUser(request)) {
            is ApiResults.Success -> {
                result.data.toInterceptor().also { resultInterceptor ->
                    checkVerifiedUserDataSource.save(resultInterceptor)
                }.let { Results.Success(it) }
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }
}


