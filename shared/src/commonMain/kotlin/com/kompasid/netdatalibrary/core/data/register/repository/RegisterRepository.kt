package com.kompasid.netdatalibrary.core.data.register.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.register.dataSource.RegisterDataSource
import com.kompasid.netdatalibrary.core.data.register.dto.interceptor.RegisterResInterceptor
import com.kompasid.netdatalibrary.core.data.register.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.register.network.RegisterApiService


class RegisterRepository(
    private val registerApiService: RegisterApiService,
    private val registerDataSource: RegisterDataSource,
) : IRegisterRepository {

    suspend fun registerByEmail(email: String, firstName: String, lastName: String, password: String): Results<RegisterResInterceptor, NetworkError> {
        return try {
            when (val result = registerApiService.registerByEmail(email, firstName, lastName, password)) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor()
                    registerDataSource.save(resultInterceptor)
                    Results.Success(resultInterceptor)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }
}