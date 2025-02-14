package com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.local.UserHistoryMembershipDataSource
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.network.UserHistoryMembershipApiService
import com.kompasid.netdatalibrary.core.data.userMembershipHistoryData.dto.OldUserHistoryMembershipResponse
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserHistoryMembershipResInterceptor


class UserMembershipHistoryRepository(
    private val userHistoryMembershipApiService: UserHistoryMembershipApiService,
    private val userHistoryMembershipDataSource: UserHistoryMembershipDataSource
) : IUserMembershipHistoryRepository {
    override suspend fun getUserMembershipHistory(): Results<UserHistoryMembershipResInterceptor, NetworkError> {
        when (val result = userHistoryMembershipApiService.getUserHistoryMembership()) {
            is ApiResults.Success -> {
                val resultInterceptor = result.data.toInterceptor()

                userHistoryMembershipDataSource.save(resultInterceptor)
                return Results.Success(resultInterceptor)
            }

            is ApiResults.Error -> {
                return Results.Error(result.error)
            }
        }
    }
}