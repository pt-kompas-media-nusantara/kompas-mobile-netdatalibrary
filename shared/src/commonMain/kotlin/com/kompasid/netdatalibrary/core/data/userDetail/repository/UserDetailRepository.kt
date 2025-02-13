package com.kompasid.netdatalibrary.core.data.userDetail.repository

import com.kompasid.netdatalibrary.core.data.userDetail.model.local.UserDetailDataSource
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.network.UserDetailApiService
import com.kompasid.netdatalibrary.core.data.userDetailData.dto.OldUserDetailResponse
import com.kompasid.netdatalibrary.core.domain.generalContent.interceptor.GeneralContentInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserDetailResInterceptor


class UserDetailRepository(
    private val userDetailApiService: UserDetailApiService,
    private val userDetailDataSource: UserDetailDataSource
) : IUserDetailRepository {
    override suspend fun getUserDetailOld(): Results<UserDetailResInterceptor, NetworkError> {
        when (val result = userDetailApiService.getUserDetailOld()) {
            is ApiResults.Success -> {
                val response = result.data

                userDetailDataSource.save(
                    response.gender ?: 0,
                    response.genderType ?: "",
                    response.dateBirth ?: "",
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
                    response.isActive ?: false,
                    response.userStatus?.isVerified ?: false,
                    response.userStatus?.phoneVerified ?: false,
                )
                return Results.Success(response.toInterceptor())
            }
            // Jika terjadi error
            is ApiResults.Error -> {
                return Results.Error(result.error)
            }
        }
    }
}