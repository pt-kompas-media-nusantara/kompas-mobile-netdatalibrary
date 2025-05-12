package com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.network.CheckRegisteredUsersApiService
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dataSource.CheckRegisteredUsersDataSource
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.mappers.toInterceptor


class CheckRegisteredUsersRepository(
    private val checkRegisteredUsersApiService: CheckRegisteredUsersApiService,
    private val checkRegisteredUsersDataSource: CheckRegisteredUsersDataSource,
) : ICheckRegisteredUsersRepository {

    suspend fun checkRegisteredUsers(value: String): Results<CheckRegisteredUsersResInterceptor, NetworkError> {
        return try {
            when (val result = checkRegisteredUsersApiService.checkRegisteredUsers(value)) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor(value)

                    checkRegisteredUsersDataSource.save(resultInterceptor)

                    Results.Success(resultInterceptor)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

}
