package com.kompasid.netdatalibrary.core.data.userDetail.repository

import com.kompasid.netdatalibrary.core.data.userDetail.dataSource.UserDetailDataSource
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.userDetail.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.network.UserDetailApiService
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor


class UserDetailRepository(
    private val userDetailApiService: UserDetailApiService,
    private val userDetailDataSource: UserDetailDataSource,
) : IUserDetailRepository {

    override suspend fun getUserDetail(): Results<UserDetailResInterceptor, NetworkError> {
        return try {
            when (val result = userDetailApiService.getUserDetail()) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor()

                    userDetailDataSource.save(resultInterceptor)

                    Results.Success(resultInterceptor)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }
}
