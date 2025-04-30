package com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource.UserMembershipDataSource
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.network.UserMembershipApiService
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserMembershipResInterceptor


class UserMembershipsRepository(
    private val userMembershipApiService: UserMembershipApiService,
    private val userMembershipDataSource: UserMembershipDataSource,
) : IUserMembershipHistoryRepository {

    suspend fun userMembership(): Results<UserMembershipResInterceptor, NetworkError> {
        return try {
            when (val result = userMembershipApiService.userMembership()) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor()

                    userMembershipDataSource.save(resultInterceptor)

                    Results.Success(resultInterceptor)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    suspend fun userHistoryMembership(): Results<UserHistoryMembershipResInterceptor, NetworkError> {
        return try {
            when (val result = userMembershipApiService.userHistoryMembership()) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor()

                    Results.Success(resultInterceptor)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }
}
