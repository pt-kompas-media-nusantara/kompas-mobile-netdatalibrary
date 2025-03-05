package com.kompasid.netdatalibrary.core.domain.personalInfo.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkVerifiedUser.repository.CheckVerifiedUserRepository
import com.kompasid.netdatalibrary.core.data.generalContent.repository.IPersonalInfoUseCase
import com.kompasid.netdatalibrary.core.data.updateProfile.repository.UpdateProfileRepository
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository.UserHistoryMembershipRepository
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.PersonalInfoInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.other.UpdateProfileType
import com.kompasid.netdatalibrary.core.domain.personalInfo.resultState.PersonalInfoState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PersonalInfoUseCase(
    private val userDetailRepository: UserDetailRepository,
    private val userHistoryMembershipRepository: UserHistoryMembershipRepository,
    private val checkVerifiedUserRepository: CheckVerifiedUserRepository,
    private val updateProfileRepository: UpdateProfileRepository
) : IPersonalInfoUseCase {


    suspend fun getUserDetailsAndMembership(): Results<Unit, NetworkError> = coroutineScope {
        runCatching {
            val tasks = listOf(
                async { userDetail() },
                async { historyMembership() }
            )

            val results = tasks.awaitAll()

            when {
                results.all { it is Results.Success } -> Results.Success(Unit)
                else -> {
                    // Batalkan semua task jika ada yang gagal
                    tasks.forEach { it.cancel() }

                    // Ambil error pertama yang ditemukan
                    val error =
                        results.filterIsInstance<Results.Error<NetworkError>>().firstOrNull()?.error
                            ?: NetworkError.ServerError

                    Results.Error(error)
                }
            }
        }.getOrElse { exception ->
            Results.Error(NetworkError.Error(exception))
        }
    }

    suspend fun userDetail(): Results<Unit, NetworkError> = coroutineScope {
        runCatching {
            userDetailRepository.getUserDetailOld()
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is Results.Success -> {
                        Results.Success(Unit)
                    }

                    is Results.Error -> Results.Error(result.error)
                }
            },
            onFailure = { Results.Error(NetworkError.Error(it)) }
        )
    }

    suspend fun historyMembership(): Results<Unit, NetworkError> = coroutineScope {
        runCatching {
            userHistoryMembershipRepository.getUserMembershipHistory()
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is Results.Success -> {
                        Results.Success(Unit)
                    }

                    is Results.Error -> Results.Error(result.error)
                }
            },
            onFailure = { Results.Error(NetworkError.Error(it)) }
        )
    }

    suspend fun checkVerifiedUser(): Results<Unit, NetworkError> = coroutineScope {
        runCatching {
            checkVerifiedUserRepository.postCheckVerifiedUser()
        }.fold(
            onSuccess = { result ->
                when (result) {
                    is Results.Success -> {
                        Results.Success(Unit)
                    }

                    is Results.Error -> Results.Error(result.error)
                }
            },
            onFailure = { Results.Error(NetworkError.Error(it)) }
        )
    }

    suspend fun updateProfile(type: UpdateProfileType): Results<Unit, NetworkError> =
        coroutineScope {
            runCatching {
                val updateResult = updateProfileRepository.updateProfile(type)

                if (updateResult is Results.Success) {
                    val userDetailResult = async { userDetail() }
                    userDetailResult.await()
                }

                updateResult
            }.getOrElse { exception ->
                Results.Error(NetworkError.Error(exception))
            }
        }


}
