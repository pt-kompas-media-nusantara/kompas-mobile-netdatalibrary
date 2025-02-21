package com.kompasid.netdatalibrary.core.domain.manageAccount.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.repository.MyRubriksRepository
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.request.SaveMyRubrikRequest
import com.kompasid.netdatalibrary.core.data.updateProfile.dto.request.UpdateProfileRequest
import com.kompasid.netdatalibrary.core.data.updateProfile.dto.request.UpdateProfileType
import com.kompasid.netdatalibrary.core.data.updateProfile.repository.UpdateProfileRepository
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository.UserHistoryMembershipRepository
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.IManageAccountUseCase
import com.kompasid.netdatalibrary.core.presentation.personalInfo.resultState.PersonalInfoResultState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


class ManageAccountUseCase(
    private val userDetailRepository: UserDetailRepository, // buat usecase sendiri
    private val historyMembershipRepository: UserHistoryMembershipRepository,
    private val myRubriksRepository: MyRubriksRepository,
    private val updateProfileRepository: UpdateProfileRepository,
    private val personalInfoResultState: PersonalInfoResultState
) : IManageAccountUseCase {

    private suspend fun updateProfileRequest(type: UpdateProfileType): UpdateProfileRequest {
        val defaultRequest = UpdateProfileRequest(
            city = personalInfoResultState.city.value,
            country = personalInfoResultState.country.value,
            dateBirth = personalInfoResultState.dateBirth.value,
            firstName = personalInfoResultState.firstName.value,
            gender = personalInfoResultState.idGender.value,
            lastName = personalInfoResultState.lastName.value,
            phoneNumber = personalInfoResultState.phoneNumber.value,
            province = personalInfoResultState.province.value,
            userName = personalInfoResultState.username.value
        )

        // Gunakan copy untuk hanya mengubah field yang diperlukan
        return when (type) {
            is UpdateProfileType.Fullname -> defaultRequest.copy(
                firstName = type.firstName,
                lastName = type.lastName
            )

            is UpdateProfileType.Gender -> defaultRequest.copy(
                gender = type.gender
            )

            is UpdateProfileType.DateBirth -> defaultRequest.copy(
                dateBirth = type.dateBirth
            )

            is UpdateProfileType.Domicile -> defaultRequest.copy(
                city = type.city,
                province = type.province,
                country = type.country
            )

            is UpdateProfileType.Email -> defaultRequest.copy(
                userName = type.email
            )

            is UpdateProfileType.PhoneNumber -> defaultRequest.copy(
                phoneNumber = type.phoneNumber
            )
        }
    }

    suspend fun updateProfile(type: UpdateProfileType): Results<Unit, NetworkError> =

        coroutineScope {
            val request = updateProfileRequest(type)

            val updateProfileDeferred = async { updateProfileRepository.updateProfile(request) }

            when (val updateResult = updateProfileDeferred.await()) {
                is Results.Success -> {
                    val userDetailDeferred =
                        async { userDetail() } // Jalankan userDetail() setelah update berhasil

                    when (val userDetailResult = userDetailDeferred.await()) {
                        is Results.Success -> Results.Success(Unit) // Jika kedua proses sukses, kembalikan Success(Unit)
                        is Results.Error -> Results.Error(userDetailResult.error) // Jika userDetail gagal, return error dari userDetail
                    }
                }

                is Results.Error -> Results.Error(updateResult.error) // Jika updateProfile gagal, langsung return error dari updateProfile
            }
        }

    suspend fun userDetail(): Results<UserDetailResInterceptor, NetworkError> {
        return userDetailRepository.getUserDetailOld()
    }

    suspend fun historyMembership(): Results<UserHistoryMembershipResInterceptor, NetworkError> {
        return historyMembershipRepository.getUserMembershipHistory()
    }

    suspend fun myRubrikList(): Results<List<MyRubriksResInterceptor>, NetworkError> {
        return myRubriksRepository.getMyRubriks()
    }

    suspend fun saveMyRubriks(request: SaveMyRubrikRequest): Results<Unit, NetworkError> {
        return myRubriksRepository.saveMyRubriks(request)
    }

    suspend fun fetchAllManageAccount(): Triple<
            Results<UserDetailResInterceptor, NetworkError>,
            Results<UserHistoryMembershipResInterceptor, NetworkError>,
            Results<List<MyRubriksResInterceptor>, NetworkError>
            > =
        coroutineScope {

            val userDetailDeferred = async { userDetail() }
            val historyMembershipDeferred = async { historyMembership() }
            val myRubrikListDeferred = async { myRubrikList() }

            val userDetailResult = userDetailDeferred.await()
            val historyMembershipResult = historyMembershipDeferred.await()
            val myRubrikListResult = myRubrikListDeferred.await()

            Triple(userDetailResult, historyMembershipResult, myRubrikListResult)
        }

}

//contoh pemanggilan fetchAllManageAccount
//suspend fun main() {
//    val manageAccountUseCase = ManageAccountUseCase(
//        userDetailRepository = UserDetailRepositoryImpl(),
//        historyMembershipRepository = UserHistoryMembershipRepositoryImpl(),
//        myRubriksRepository = MyRubriksRepositoryImpl()
//    )
//
//    val (userDetail, historyMembership, myRubriks) = manageAccountUseCase.fetchAllData()
//
//    println("User Detail: $userDetail")
//    println("History Membership: $historyMembership")
//    println("My Rubriks: $myRubriks")
//}


