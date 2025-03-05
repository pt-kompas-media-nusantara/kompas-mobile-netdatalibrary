package com.kompasid.netdatalibrary.core.data.userDetail.repository

import com.kompasid.netdatalibrary.core.data.userDetail.dataSource.UserDetailDataSource
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.userDetail.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.network.UserDetailApiService
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.PersonalInfoInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.resultState.PersonalInfoState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class UserDetailRepository(
    private val userDetailApiService: UserDetailApiService,
    private val userDetailDataSource: UserDetailDataSource,
    private val personalInfoState: PersonalInfoState,
) : IUserDetailRepository {

    override suspend fun getUserDetailOld(): Results<UserDetailResInterceptor, NetworkError> =
        runCatching {
            userDetailApiService.getUserDetail()
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is ApiResults.Success -> {
                        val resultInterceptor = result.data.toInterceptor()
                        coroutineScope {
                            launch { runCatching { userDetailDataSource.save(resultInterceptor) } }
                            launch {
                                runCatching {
                                    personalInfoState.updatePersonalInfo(
                                        PersonalInfoInterceptor(userDetails = resultInterceptor)
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