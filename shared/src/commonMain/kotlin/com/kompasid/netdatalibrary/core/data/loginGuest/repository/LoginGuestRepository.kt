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

    override suspend fun postLoginGuest(): Results<String, NetworkError> {
        return when (val result = loginGuestApiService.postLoginGuest()) {
            is ApiResults.Success -> {
                val accessToken = result.data.data?.accessToken.orEmpty()
                val refreshToken = result.data.data?.refreshToken.orEmpty()

                loginGuestDataSource.save(accessToken, refreshToken)
                Results.Success(accessToken)
            }

            is ApiResults.Error -> Results.Error(result.error)
        }
    }
}

