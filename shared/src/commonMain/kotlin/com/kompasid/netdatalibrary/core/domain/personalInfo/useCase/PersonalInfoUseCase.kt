package com.kompasid.netdatalibrary.core.domain.personalInfo.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.generalContent.repository.IPersonalInfoUseCase
import com.kompasid.netdatalibrary.core.data.updateProfile.repository.UpdateProfileRepository
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailsAndMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository.UserMembershipsRepository
import com.kompasid.netdatalibrary.helper.SupportSettingsHelper
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.helpers.AppResult
import com.kompasid.netdatalibrary.helpers.logged
import com.kompasid.netdatalibrary.helpers.zip3
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class PersonalInfoUseCase(
    private val userDetailRepository: UserDetailRepository,
    private val userMembershipsRepository: UserMembershipsRepository,
    private val updateProfileRepository: UpdateProfileRepository,
    private val settingsHelper: SettingsHelper
) : IPersonalInfoUseCase {

    suspend fun getUserDetailsAndMembership(): Results<UserDetailsAndMembershipResInterceptor, NetworkError> =
        coroutineScope {
            val detailDeferred = async { userDetail() }
            val memberDeferred = async { userMembership() }
            val historyDeferred = async { userHistoryMembership() }

            val detailRes: AppResult<UserDetailResInterceptor> = detailDeferred.await()
            val memberRes: AppResult<UserMembershipResInterceptor> = memberDeferred.await()
            val historyRes: AppResult<UserHistoryMembershipResInterceptor> = historyDeferred.await()

            // Anotasi opsional kalau mau bantu inference lokal:
            val merged: AppResult<UserDetailsAndMembershipResInterceptor> =
                zip3(detailRes, memberRes, historyRes) { detail, member, history ->
                    UserDetailsAndMembershipResInterceptor(
                        userDetail = detail,
                        userMembership = member,
                        userHistoryMembership = history
                    )
                }
            merged
        }


    suspend fun userDetail(): Results<UserDetailResInterceptor, NetworkError> {
        return try {
            userDetailRepository.getUserDetail().logged(prefix = "UseCase: userDetail")
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    suspend fun userMembership(): Results<UserMembershipResInterceptor, NetworkError> {
        return try {
            userMembershipsRepository.userMembership().logged(prefix = "UseCase: userMembership")
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }

    suspend fun userHistoryMembership(): Results<UserHistoryMembershipResInterceptor, NetworkError> {
        return try {
            userMembershipsRepository.userHistoryMembership().logged(prefix = "UseCase: userHistoryMembership")
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
//
//    // -------- Utilities --------
//
//    /** Gabungkan 3 Results. Sukses jika ketiganya Success, else kembalikan error pertama yang ada. */
//    private inline fun <A, B, C, R> zip3(
//        ra: Results<A, NetworkError>,
//        rb: Results<B, NetworkError>,
//        rc: Results<C, NetworkError>,
//        combine: (A, B, C) -> R
//    ): Results<R, NetworkError> {
//        return when {
//            ra is Results.Success && rb is Results.Success && rc is Results.Success ->
//                Results.Success(combine(ra.data, rb.data, rc.data))
//
//            ra is Results.Error -> Results.Error(ra.error)
//            rb is Results.Error -> Results.Error(rb.error)
//            rc is Results.Error -> Results.Error(rc.error)
//
//            else -> Results.Error(NetworkError.ServerError) // fallback defensif
//        }
//    }

}
