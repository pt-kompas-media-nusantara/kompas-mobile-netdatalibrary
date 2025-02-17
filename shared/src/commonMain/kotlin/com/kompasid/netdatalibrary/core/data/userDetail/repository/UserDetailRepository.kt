package com.kompasid.netdatalibrary.core.data.userDetail.repository

import com.kompasid.netdatalibrary.core.data.userDetail.dataSource.UserDetailDataSource
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.network.UserDetailApiService
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.resultState.UserDetailResultState


class UserDetailRepository(
    private val userDetailApiService: UserDetailApiService,
    private val userDetailDataSource: UserDetailDataSource,
    private val userDetailResultState: UserDetailResultState
) : IUserDetailRepository {

    override suspend fun getUserDetailOld(): Results<UserDetailResInterceptor, NetworkError> {
        return when (val result = userDetailApiService.getUserDetail()) {
            is ApiResults.Success -> {
                result.data.toInterceptor().also { resultInterceptor ->
                    userDetailDataSource.save(resultInterceptor)

                    userDetailResultState.apply {
                        if (userDetail.value != resultInterceptor) updateUserDetail(
                            resultInterceptor
                        )
                    }
                }.let { Results.Success(it) }
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }

//    override suspend fun getUserDetailOld(): Results<UserDetailResInterceptor, NetworkError> {
//        when (val result = userDetailApiService.getUserDetail()) {
//            is ApiResults.Success -> {
//                val response = result.data
//
//                userDetailDataSource.save(
//                    response.gender ?: 0,
//                    response.genderType ?: "",
//                    response.dateBirth ?: "",
//                    response.userId ?: "",
//                    response.firstName ?: "",
//                    response.lastName ?: "",
//                    response.email ?: "",
//                    response.userGuid ?: "",
//                    response.phoneNumber ?: "",
//                    response.countryCode ?: "",
//                    response.country ?: "",
//                    response.province ?: "",
//                    response.city ?: "",
//                    response.isActive ?: false,
//                    response.userStatus?.isVerified ?: false,
//                    response.userStatus?.phoneVerified ?: false,
//                )
//                userDetailResultState
//                return Results.Success(response.toInterceptor())
//            }
//            // Jika terjadi error
//            is ApiResults.Error -> {
//                return Results.Error(result.error)
//            }
//        }
//    }
}