package com.kompasid.netdatalibrary.core.domain.personalInfo.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.generalContent.repository.IPersonalInfoUseCase
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userMembershipHistory.repository.UserMembershipHistoryRepository
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserMembershipHistoryResInterceptor
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope



class PersonalInfoUseCase(
    private val userDetailRepository: UserDetailRepository,
    private val historyRepository: UserMembershipHistoryRepository
) : IPersonalInfoUseCase {

    suspend fun getUserDetailsAndMembership(): Results<Pair<UserDetailResInterceptor, UserMembershipHistoryResInterceptor>, NetworkError> {
        return try {
            coroutineScope {
                val userDetailDeferred = async { userDetail() }
                val historyMembershipDeferred = async { historyMembersip() }

                val userDetailResult = userDetailDeferred.await()
                val historyMembershipResult = historyMembershipDeferred.await()

                when {
                    userDetailResult is Results.Success && historyMembershipResult is Results.Success -> {
                        Results.Success(Pair(userDetailResult.data, historyMembershipResult.data))
                    }

                    userDetailResult is Results.Error -> userDetailResult
                    historyMembershipResult is Results.Error -> historyMembershipResult
                    else -> Results.Error(NetworkError.ServerError) // Error fallback jika kondisi lain
                }
            }
        } catch (e: Exception) {
            // Log atau handle error yang lebih spesifik di sini jika dibutuhkan
            return Results.Error(NetworkError.Error(e))
        }
    }

    override suspend fun userDetail(): Results<UserDetailResInterceptor, NetworkError> {
        return userDetailRepository.getUserDetailOld()
    }

    suspend fun historyMembersip(): Results<UserMembershipHistoryResInterceptor, NetworkError> {
        when (val response = historyRepository.getUserMembershipHistory()) {
            is Results.Success -> {
                val userDetail = response.data
                val userMembershipHistoryResInterceptor = UserMembershipHistoryResInterceptor(
                    expired = userDetail.result?.user?.expired ?: "",
                    isActive = userDetail.result?.user?.isActive ?: "",
                    startDate = userDetail.result?.user?.startDate ?: "",
                    endDate = userDetail.result?.user?.endDate ?: "",
                    totalGracePeriod = userDetail.result?.user?.totalGracePeriod ?: 0,
                    gracePeriod = userDetail.result?.user?.gracePeriod ?: false,

                    )
                return Results.Success(userMembershipHistoryResInterceptor)
            }

            is Results.Error -> {
                return Results.Error(response.error)
            }
        }
    }
}