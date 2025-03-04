package com.kompasid.netdatalibrary.core.data.userDetail.repository

import com.kompasid.netdatalibrary.core.data.userDetail.dataSource.UserDetailDataSource
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.userDetail.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.network.UserDetailApiService
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope


class UserDetailRepository(
    private val userDetailApiService: UserDetailApiService,
    private val userDetailDataSource: UserDetailDataSource
) : IUserDetailRepository {

    override suspend fun getUserDetailOld(): Results<UserDetailResInterceptor, NetworkError> =
        runCatching {
            userDetailApiService.getUserDetail()
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is ApiResults.Success -> {
                        val resultInterceptor = result.data.toInterceptor()
                        supervisorScope { launch { userDetailDataSource.save(resultInterceptor) } }
                        Results.Success(resultInterceptor)
                    }

                    is ApiResults.Error -> Results.Error(result.error)
                }
            },
            onFailure = { Results.Error(NetworkError.Error(it)) }
        )
}