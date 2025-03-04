package com.kompasid.netdatalibrary.core.domain.personalInfo.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.generalContent.repository.IPersonalInfoUseCase
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository.UserHistoryMembershipRepository
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.PersonalInfoInterceptor
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.supervisorScope

class PersonalInfoState {
    private val _state: MutableStateFlow<PersonalInfoInterceptor> =
        MutableStateFlow(PersonalInfoInterceptor())

    private val state: StateFlow<PersonalInfoInterceptor> = _state.asStateFlow()

    suspend fun updatePersonalInfo(newData: PersonalInfoInterceptor) {
        _state.value = newData
    }

    suspend fun getPersonalInfo(): PersonalInfoInterceptor {
        return state.value
    }
}


class PersonalInfoUseCase(
    private val personalInfoState: PersonalInfoState,
    private val userDetailRepository: UserDetailRepository,
    private val userHistoryMembershipRepository: UserHistoryMembershipRepository,
) : IPersonalInfoUseCase {


    suspend fun getUserDetailsAndMembership(): Results<Pair<UserDetailResInterceptor, UserHistoryMembershipResInterceptor>, NetworkError> {
        return try {
            supervisorScope {  // Memastikan coroutine dapat berjalan terpisah sebelum penanganan error
                val userDetailDeferred = async { userDetail() }
                val historyMembershipDeferred = async { historyMembersip() }

                try {
                    val userDetailResult = userDetailDeferred.await()
                    val historyMembershipResult = historyMembershipDeferred.await()

                    when {
                        userDetailResult is Results.Success && historyMembershipResult is Results.Success -> {

                            personalInfoState.updatePersonalInfo(
                                PersonalInfoInterceptor(
                                    userDetails = userDetailResult.data,
                                    userHistoryMembership = historyMembershipResult.data
                                )
                            )

                            Results.Success(
                                Pair(
                                    userDetailResult.data,
                                    historyMembershipResult.data
                                )
                            )
                        }

                        userDetailResult is Results.Error -> {
                            historyMembershipDeferred.cancel() // Batalkan yang lain
                            userDetailResult
                        }

                        historyMembershipResult is Results.Error -> {
                            userDetailDeferred.cancel() // Batalkan yang lain
                            historyMembershipResult
                        }

                        else -> {
                            // Ambil error dari salah satu jika keduanya gagal
                            val error = when {
                                userDetailResult is Results.Error -> userDetailResult.error
                                historyMembershipResult is Results.Error -> historyMembershipResult.error
                                else -> NetworkError.ServerError
                            }
                            Results.Error(error) // Gunakan error yang lebih spesifik
                        }
                    }
                } catch (e: Exception) {
                    userDetailDeferred.cancel()
                    historyMembershipDeferred.cancel()
                    Results.Error(NetworkError.Error(e))
                }
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    override suspend fun userDetail(): Results<UserDetailResInterceptor, NetworkError> =
        userDetailRepository.getUserDetailOld()

    override suspend fun historyMembersip(): Results<UserHistoryMembershipResInterceptor, NetworkError> =
        userHistoryMembershipRepository.getUserMembershipHistory()

}
