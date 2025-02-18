package com.kompasid.netdatalibrary.core.data.loginGuest.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginGuest.dataSource.LoginGuestDataSource
import com.kompasid.netdatalibrary.core.data.loginGuest.network.LoginGuestApiService

class LoginGuestRepository(
    private val loginGuestApiService: LoginGuestApiService,
    private val loginGuestDataSource: LoginGuestDataSource
) : ILoginGuestRepository {

    override suspend fun postLoginGuest(): Results<Unit, NetworkError> {
        return when (val result = loginGuestApiService.postLoginGuest()) {
            is ApiResults.Success -> {
                val result = result.data

                loginGuestDataSource.save(result.accessToken ?: "", result.refreshToken ?: "")
                return Results.Success(Unit)
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }
}
