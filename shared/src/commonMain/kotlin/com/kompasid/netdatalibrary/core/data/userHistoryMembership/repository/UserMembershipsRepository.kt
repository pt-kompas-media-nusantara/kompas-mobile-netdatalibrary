package com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource.UserMembershipDataSource
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.network.UserMembershipApiService
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.PersonalInfoInterceptor

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class UserMembershipsRepository(
    private val userMembershipApiService: UserMembershipApiService,
    private val userMembershipDataSource: UserMembershipDataSource,
) : IUserMembershipHistoryRepository {

    override suspend fun getUserMembershipHistory(): Results<UserHistoryMembershipResInterceptor, NetworkError> =
        runCatching {
            userMembershipApiService.getUserHistoryMembership()
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is ApiResults.Success -> {
                        val resultInterceptor = result.data.toInterceptor()
                        coroutineScope {
                            launch {
                                runCatching {
                                    userMembershipDataSource.save(
                                        resultInterceptor
                                    )
                                }
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
