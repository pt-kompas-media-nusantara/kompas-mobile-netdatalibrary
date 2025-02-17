package com.kompasid.netdatalibrary.core.domain.personalInfo.useCase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor

interface IManageAccountUseCase {
    suspend fun myRubriks(): Results<List<MyRubriksResInterceptor>, NetworkError>
}