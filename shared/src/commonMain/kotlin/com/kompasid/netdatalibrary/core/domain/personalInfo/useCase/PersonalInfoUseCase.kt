package com.kompasid.netdatalibrary.core.domain.personalInfo.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.generalContent.repository.IPersonalInfoUseCase
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository.UserHistoryMembershipRepository
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


class PersonalInfoUseCase(
    private val userDetailRepository: UserDetailRepository,
    private val userHistoryMembershipRepository: UserHistoryMembershipRepository,
) : IPersonalInfoUseCase {

    suspend fun getUserDetailsAndMembership(): Results<Pair<UserDetailResInterceptor, UserHistoryMembershipResInterceptor>, NetworkError> {
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
        val result = userDetailRepository.getUserDetailOld()

        return result
    }

    override suspend fun historyMembersip(): Results<UserHistoryMembershipResInterceptor, NetworkError> {
        val result = userHistoryMembershipRepository.getUserMembershipHistory()
        return result
    }
}