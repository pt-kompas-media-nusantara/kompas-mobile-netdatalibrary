package com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource.UserHistoryMembershipDataSource
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.network.UserHistoryMembershipApiService
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope


class UserHistoryMembershipRepository(
    private val userHistoryMembershipApiService: UserHistoryMembershipApiService,
    private val userHistoryMembershipDataSource: UserHistoryMembershipDataSource
) : IUserMembershipHistoryRepository {

    override suspend fun getUserMembershipHistory(): Results<UserHistoryMembershipResInterceptor, NetworkError> =
        runCatching {
            userHistoryMembershipApiService.getUserHistoryMembership()
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is ApiResults.Success -> {
                        val resultInterceptor = result.data.toInterceptor()
                        supervisorScope {
                            launch {
                                userHistoryMembershipDataSource.save(
                                    resultInterceptor
                                )
                            }
                        }
                        Results.Success(resultInterceptor)
                    }

                    is ApiResults.Error -> Results.Error(result.error)
                }
            },
            onFailure = { Results.Error(NetworkError.Error(it)) }
        )


}
