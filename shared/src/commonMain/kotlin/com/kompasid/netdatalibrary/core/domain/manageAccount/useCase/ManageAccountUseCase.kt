package com.kompasid.netdatalibrary.core.domain.manageAccount.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.repository.MyRubriksRepository
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository.UserHistoryMembershipRepository
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import com.kompasid.netdatalibrary.core.domain.manageAccount.resultState.ManageAccountState
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.IManageAccountUseCase


class ManageAccountUseCase(
    private val myRubriksRepository: MyRubriksRepository,
    private val userHistoryMembershipRepository: UserHistoryMembershipRepository,
) : IManageAccountUseCase {

    override suspend fun myRubriks(): Results<List<MyRubriksResInterceptor>, NetworkError> {
        return myRubriksRepository.getMyRubriks()
    }
}