package com.kompasid.netdatalibrary.netData.data.loginGuestData

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results


class LoginGuestRepository(
    private val service: LoginGuestApiService,
    private val dataSource: LoginGuestDataSource,
) {

    suspend fun postLoginGuest(): Results<Unit, NetworkError> {
        when (val result = service.postLoginGuest()) {
            is ApiResults.Success -> {
                val response = result.data

                dataSource.save(response.accessToken ?: "", response.refreshToken ?: "")
                return Results.Success(Unit)
            }
            // Jika terjadi error
            is ApiResults.Error -> {
                return Results.Error(result.error)
            }
        }
    }
}

/*
PENGGUNAAN USE CASE
val loginGuestRepository = LoginGuestRepository(service, dataSource)

val result = loginGuestRepository.postLoginGuest()

result.onSuccess { success ->
    println("Success: ${success.result}")
}.onError { error ->
    when (error) {
        is FailedInterceptor.RequestTimeout -> println("Request timeout!")
        is FailedInterceptor.Unauthorized -> println("Unauthorized access!")
        else -> println("Unknown error occurred.")
    }
}

*/
