package com.kompasid.netdatalibrary.core.data.myRubriks.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.request.SaveMyRubrikRequest

interface IMyRubriksRepository {
    suspend fun getMyRubriks(): Results<List<MyRubriksResInterceptor>, NetworkError>
    suspend fun saveMyRubriks(request: SaveMyRubrikRequest): Results<Unit, NetworkError>
}