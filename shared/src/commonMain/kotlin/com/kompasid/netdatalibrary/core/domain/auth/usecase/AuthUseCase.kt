package com.kompasid.netdatalibrary.core.domain.auth.usecase

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.loginEmail.repository.LoginEmailRepository
import com.kompasid.netdatalibrary.core.data.loginEmail.models.dto.LoginEmailRequest
import com.kompasid.netdatalibrary.core.data.loginGuest.repository.LoginGuestRepository
import com.kompasid.netdatalibrary.core.data.logout.repository.LogoutRepository
import com.kompasid.netdatalibrary.core.domain.personalInfo.useCase.PersonalInfoUseCase
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserMembershipHistoryResInterceptor

interface LoginGuestRepositoryContoh {
    suspend fun postLoginGuest(): Results<Unit, NetworkError>
}

class AuthUseCase(
    private val loginGuestRepository: LoginGuestRepository,
    private val loginEmailRepository: LoginEmailRepository,
    private val logoutRepository: LogoutRepository,
    private val personalInfoUseCase: PersonalInfoUseCase
) {

    suspend fun loginAnonOne(): Results<LoginAnonResInterceptor, NetworkError> {
        val result = loginGuestRepository.postLoginGuest()
        return Results.Success(LoginAnonResInterceptor("nurirppan"))
    }

    suspend fun loginAnonTwo(): Results<Pair<LoginAnonResInterceptor, LoginAnonResInterceptor>, NetworkError> {
        val result = loginGuestRepository.postLoginGuest()
        return Results.Success(Pair(LoginAnonResInterceptor("nurirppan"), LoginAnonResInterceptor("pangestu")))
    }

    suspend fun loginAnon(): Results<Unit, NetworkError> {
        val result = loginGuestRepository.postLoginGuest()
        return result
    }

    suspend fun loginByEmail(request: LoginEmailRequest): Results<Pair<Unit, Pair<UserDetailResInterceptor, UserMembershipHistoryResInterceptor>>, NetworkError> {
        // Menjalankan login email terlebih dahulu
        val loginResult = loginEmailRepository.postLoginEmail(request)

        when (loginResult) {
            is Results.Success -> {
                // Jika login berhasil, jalankan getUserDetailsAndMembership
                val userDetailsAndMembershipResult =
                    personalInfoUseCase.getUserDetailsAndMembership()

                // Periksa hasil dari getUserDetailsAndMembership
                when (userDetailsAndMembershipResult) {
                    is Results.Success -> {
                        // Jika keduanya sukses, kembalikan semua data sukses
                        return Results.Success(
                            Pair(
                                loginResult.data,
                                Pair(
                                    userDetailsAndMembershipResult.data.first,
                                    userDetailsAndMembershipResult.data.second
                                )
                            )
                        )
                    }

                    is Results.Error -> {
                        // Jika getUserDetailsAndMembership gagal, kembalikan hanya error tersebut
                        return Results.Error(userDetailsAndMembershipResult.error)
                    }
                }
            }

            is Results.Error -> {
                // Jika login gagal, kembalikan error login saja
                return Results.Error(loginResult.error)
            }
        }
    }

    suspend fun logout(): Results<Unit, NetworkError> {
        val result = logoutRepository.postLogout()
        return result
    }


}

data class LoginAnonResInterceptor(
    val userId: String
)