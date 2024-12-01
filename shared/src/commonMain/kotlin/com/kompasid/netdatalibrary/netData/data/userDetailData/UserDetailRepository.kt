package com.kompasid.netdatalibrary.netData.data.userDetailData

import com.kompasid.app.netdatamodule.Example.Data.UserDetailData.UserDetailDataSource
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.netData.data.userDetailData.dto.UserDetailResponse


class UserDetailRepository(
    private val userDetailApiService: UserDetailApiService,
    private val userDetailDataSource: UserDetailDataSource
) {
    suspend fun getUserDetail(): Results<UserDetailResponse, NetworkError> {
        when (val result = userDetailApiService.getUserDetail()) {
            is ApiResults.Success -> {
                val response = result.data

                userDetailDataSource.save(
                    response.userId ?: "",
                    response.firstName ?: "",
                    response.lastName ?: "",
                    response.email ?: "",
                    response.userGuid ?: "",
                    response.phoneNumber ?: "",
                    response.countryCode ?: "",
                    response.country ?: "",
                    response.province ?: "",
                    response.city ?: "",
                )
                return Results.Success(response)
            }
            // Jika terjadi error
            is ApiResults.Error -> {
                return Results.Error(result.error)
            }
        }
    }
}