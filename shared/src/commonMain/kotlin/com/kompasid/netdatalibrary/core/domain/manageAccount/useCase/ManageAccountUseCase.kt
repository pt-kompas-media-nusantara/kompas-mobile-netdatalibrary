package com.kompasid.netdatalibrary.core.domain.manageAccount.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.repository.MyRubriksRepository
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.repository.UserDetailRepository
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository.UserHistoryMembershipRepository
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.IManageAccountUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


class ManageAccountUseCase(
    private val userDetailRepository: UserDetailRepository,
    private val historyMembershipRepository: UserHistoryMembershipRepository,
    private val myRubriksRepository: MyRubriksRepository
) : IManageAccountUseCase {

    suspend fun userDetail(): Results<UserDetailResInterceptor, NetworkError> {
        return userDetailRepository.getUserDetailOld()
    }

    suspend fun historyMembership(): Results<UserHistoryMembershipResInterceptor, NetworkError> {
        return historyMembershipRepository.getUserMembershipHistory()
    }

    suspend fun myRubrikList(): Results<List<MyRubriksResInterceptor>, NetworkError> {
        return myRubriksRepository.getMyRubriks()
    }

    suspend fun fetchAllManageAccount(): Triple<
            Results<UserDetailResInterceptor, NetworkError>,
            Results<UserHistoryMembershipResInterceptor, NetworkError>,
            Results<List<MyRubriksResInterceptor>, NetworkError>
            > = coroutineScope {
        val userDetailDeferred = async {
            userDetailRepository.getUserDetailOld()
        }
        val historyMembershipDeferred = async {
            historyMembershipRepository.getUserMembershipHistory()
        }
        val myRubriksDeferred = async {
            myRubriksRepository.getMyRubriks()
        }

        Triple(
            userDetailDeferred.await(),
            historyMembershipDeferred.await(),
            myRubriksDeferred.await()
        )
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
