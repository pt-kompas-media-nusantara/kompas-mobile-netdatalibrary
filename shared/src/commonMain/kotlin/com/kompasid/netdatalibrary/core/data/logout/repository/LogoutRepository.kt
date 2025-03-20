package com.kompasid.netdatalibrary.core.data.logout.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.logout.network.LogoutApiService
import com.kompasid.netdatalibrary.core.data.logout.dataSource.LogoutDataSource


class LogoutRepository(
    private val logoutApiService: LogoutApiService,
    private val logoutDatasource: LogoutDataSource,
) : ILogoutRepository {

    override suspend fun postLogout(): Results<Unit, NetworkError> {
        return try {
            val apiResult = logoutApiService.postLogout()

            when (apiResult) {
                is ApiResults.Success -> {
                    logoutDatasource.logout() // Dipanggil setelah API logout sukses
                    Results.Success(Unit)
                }

                is ApiResults.Error -> Results.Error(apiResult.error)
            }
        } catch (e: Exception) {
            Results.Error(NetworkError.Error(e))
        }
    }
}