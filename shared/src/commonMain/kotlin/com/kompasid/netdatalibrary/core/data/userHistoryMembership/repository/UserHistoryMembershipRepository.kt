package com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dataSource.UserDetailDataSource
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.network.UserDetailApiService
import com.kompasid.netdatalibrary.core.data.userDetail.repository.IUserDetailRepository
import com.kompasid.netdatalibrary.core.data.userDetail.resultState.UserDetailResultState
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource.UserHistoryMembershipDataSource
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.network.UserHistoryMembershipApiService
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.resultState.UserHistoryMembershipResultState


class UserHistoryMembershipRepository(
    private val userHistoryMembershipApiService: UserHistoryMembershipApiService,
    private val userHistoryMembershipDataSource: UserHistoryMembershipDataSource
) : IUserMembershipHistoryRepository {
    override suspend fun getUserMembershipHistory(): Results<UserHistoryMembershipResInterceptor, NetworkError> {
        return when (val result = userHistoryMembershipApiService.getUserHistoryMembership()) {
            is ApiResults.Success -> {
                result.data.toInterceptor().also { resultInterceptor ->
                    userHistoryMembershipDataSource.save(resultInterceptor)
                }.let { Results.Success(it) }
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }

}
