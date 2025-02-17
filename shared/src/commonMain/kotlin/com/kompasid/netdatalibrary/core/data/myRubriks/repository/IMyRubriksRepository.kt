package com.kompasid.netdatalibrary.core.data.myRubriks.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor

interface IMyRubriksRepository {
    suspend fun getMyRubriks(): Results<List<MyRubriksResInterceptor>, NetworkError>
}