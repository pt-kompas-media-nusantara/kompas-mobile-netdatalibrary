package com.kompasid.netdatalibrary.core.domain.personalInfo.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.repository.CheckRegisteredUsersRepository
import com.kompasid.netdatalibrary.core.data.generalContent.repository.IPersonalInfoUseCase
import com.kompasid.netdatalibrary.core.data.updateProfile.repository.UpdateProfileRepository
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository.UserMembershipsRepository
import com.kompasid.netdatalibrary.helpers.logged
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope

class PersonalInfoUseCase(
    private val userDetailRepository: UserDetailRepository,
    private val userMembershipsRepository: UserMembershipsRepository,
    private val checkRegisteredUsersRepository: CheckRegisteredUsersRepository,
    private val updateProfileRepository: UpdateProfileRepository
) : IPersonalInfoUseCase {


    suspend fun getUserDetailsAndMembership(): Results<Pair<UserDetailResInterceptor, UserHistoryMembershipResInterceptor>, NetworkError> =
        supervisorScope {
            val userDetailDeferred = async { userDetail() }
            val historyMembershipDeferred = async { historyMembership() }

            try {
                val userDetailResult = userDetailDeferred.await()
                val historyMembershipResult = historyMembershipDeferred.await()

                when {
                    userDetailResult is Results.Success && historyMembershipResult is Results.Success -> {
                        Results.Success(userDetailResult.data to historyMembershipResult.data)
                    }

                    userDetailResult is Results.Error -> {
                        historyMembershipDeferred.cancel()
                        Results.Error(userDetailResult.error)
                    }

                    historyMembershipResult is Results.Error -> {
                        userDetailDeferred.cancel()
                        Results.Error(historyMembershipResult.error)
                    }

                    else -> Results.Error(NetworkError.ServerError)
                }
            } catch (e: Exception) {
                userDetailDeferred.cancel()
                historyMembershipDeferred.cancel()
                Results.Error(NetworkError.Error(e))
            }
        }

    suspend fun userDetail(): Results<UserDetailResInterceptor, NetworkError> {
        return try {
            userDetailRepository.getUserDetailOld().logged(prefix = "userDetail")
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    suspend fun historyMembership(): Results<UserHistoryMembershipResInterceptor, NetworkError> {
        return try {
            userMembershipsRepository.getUserMembershipHistory().logged(prefix = "historyMembership")
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    suspend fun checkRegisteredUsers(value: String): Results<CheckRegisteredUsersResInterceptor, NetworkError> {
        return try {
            checkRegisteredUsersRepository.checkRegisteredUsers(value).logged(prefix = "checkRegisteredUsers")
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

//    suspend fun checkRegisteredUsers(value: String): Results<Unit, NetworkError> = coroutineScope {
//        runCatching {
//            checkVerifiedUserRepository.checkRegisteredUsers(value)
//        }.fold(
//            onSuccess = { result ->
//                when (result) {
//                    is Results.Success -> {
//                        Results.Success(Unit)
//                    }
//
//                    is Results.Error -> Results.Error(result.error)
//                }
//            },
//            onFailure = { Results.Error(NetworkError.Error(it)) }
//        )
//    }

//    suspend fun updateProfile(type: UpdateProfileType): Results<Unit, NetworkError> =
//        coroutineScope {
//            runCatching {
//                val updateProfileDeferred =
//                    async { updateProfileRepository.updateProfile(type) } // Jalankan secara concurrent
//                val updateResult = updateProfileDeferred.await() // Tunggu hasil update
//
//                if (updateResult is Results.Success) {
//                    val userDetailDeferred =
//                        async { userDetail() } // Jalankan userDetail() jika update sukses
//                    val userDetailResult = userDetailDeferred.await() // Tunggu hasil userDetail()
//
//                    if (userDetailResult is Results.Success) {
//                        Results.Success(Unit) // Keduanya sukses
//                    } else {
//                        userDetailResult // Jika userDetail() gagal, kembalikan error
//                    }
//                } else {
//                    updateResult // Jika updateProfile() gagal, langsung kembalikan error
//                }
//            }.getOrElse { exception ->
//                Results.Error(NetworkError.Error(exception)) // Tangani error tak terduga
//            }
//        }


}
