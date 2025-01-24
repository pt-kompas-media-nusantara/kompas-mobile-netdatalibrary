package com.kompasid.netdatalibrary.core.data.userMembershipHistory.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.userMembershipHistory.model.local.UserMembershipHistoryDataSource
import com.kompasid.netdatalibrary.core.data.userMembershipHistory.network.UserMembershipHistoryApiService
import com.kompasid.netdatalibrary.core.data.userMembershipHistoryData.dto.UserMembershipHistoryResponse


class UserMembershipHistoryRepository(
    private val userMembershipHistoryApiService: UserMembershipHistoryApiService,
    private val userMembershipHistoryDataSource: UserMembershipHistoryDataSource
) {
    suspend fun getUserMembershipHistory(): Results<UserMembershipHistoryResponse, NetworkError> {
        when (val result = userMembershipHistoryApiService.getUserMembershipHistory()) {
            is ApiResults.Success -> {
                val response = result.data

                userMembershipHistoryDataSource.save(
                    response.result?.user?.expired ?: "",
                    response.result?.user?.isActive ?: "",
                    response.result?.user?.startDate ?: "",
                    response.result?.user?.endDate ?: "",
                    response.result?.user?.totalGracePeriod ?: 0,
                    response.result?.user?.gracePeriod ?: false
                )
                return Results.Success(response)
            }

            is ApiResults.Error -> {
                return Results.Error(result.error)
            }
        }
    }
}