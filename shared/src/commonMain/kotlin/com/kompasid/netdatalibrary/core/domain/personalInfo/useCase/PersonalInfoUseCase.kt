package com.kompasid.netdatalibrary.core.domain.personalInfo.useCase

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.generalContent.repository.IPersonalInfoUseCase
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository.UserHistoryMembershipRepository
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.PersonalInfoInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.resultState.PersonalInfoState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope


class PersonalInfoUseCase(
    private val personalInfoState: PersonalInfoState,
    private val userDetailRepository: UserDetailRepository,
    private val userHistoryMembershipRepository: UserHistoryMembershipRepository,
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
                        personalInfoState.updatePersonalInfo(
                            PersonalInfoInterceptor(
                                userDetails = result.data
                            )
                        )
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
                        personalInfoState.updatePersonalInfo(
                            PersonalInfoInterceptor(
                                userHistoryMembership = result.data
                            )
                        )
                        Results.Success(Unit)
                    }

                    is Results.Error -> Results.Error(result.error)
                }
            },
            onFailure = { Results.Error(NetworkError.Error(it)) }
        )
    }


}
