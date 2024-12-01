package com.kompasid.netdatalibrary.netData.domain.personalInfoDomain

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.netData.data.userDetailData.UserDetailRepository
import com.kompasid.netdatalibrary.netData.data.userMembershipHistoryData.UserMembershipHistoryRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class PersonalInfoUseCase(
    private val userDetailRepository: UserDetailRepository,
    private val historyRepository: UserMembershipHistoryRepository
) {

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

    private suspend fun userDetail(): Results<UserDetailResInterceptor, NetworkError> {
        when (val response = userDetailRepository.getUserDetail()) {
            is Results.Success -> {
                val userDetail = response.data
                val userDetailResInterceptor = UserDetailResInterceptor(
                    userId = userDetail.userId ?: "",
                    firstName = userDetail.firstName ?: "",
                    lastName = userDetail.lastName ?: "",
                    email = userDetail.email ?: "",
                    userGuid = userDetail.userGuid ?: "",
                    isActive = userDetail.isActive ?: false,
                    userStatus = UserStatus(
                        isVerified = userDetail.userStatus?.isVerified ?: false,
                        phoneVerified = userDetail.userStatus?.phoneVerified ?: false
                    ),
                    phoneNumber = userDetail.phoneNumber ?: "",
                    countryCode = userDetail.countryCode ?: "",
                    dateBirth = userDetail.dateBirth ?: "",
                    country = userDetail.country ?: "",
                    province = userDetail.province ?: "",
                    city = userDetail.city ?: ""
                )
                return Results.Success(userDetailResInterceptor)
            }

            is Results.Error -> {
                return Results.Error(response.error)
            }
        }

    }

    private suspend fun historyMembersip(): Results<UserMembershipHistoryResInterceptor, NetworkError> {
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