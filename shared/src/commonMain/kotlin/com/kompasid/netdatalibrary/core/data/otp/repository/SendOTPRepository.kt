package com.kompasid.netdatalibrary.core.data.otp.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dataSource.CheckVerifiedUserDataSource
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.dto.interceptor.CheckRegisteredUsersResInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.network.CheckRegisteredUsersApiService
import com.kompasid.netdatalibrary.core.data.checkRegisteredUsers.repository.ICheckRegisteredUsersRepository
import com.kompasid.netdatalibrary.core.data.otp.dto.interceptor.SendOTPResInterceptor
import com.kompasid.netdatalibrary.core.data.otp.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.otp.network.SendOTPApiService


class SendOTPRepository(
    private val sendOTPApiService: SendOTPApiService,
) : ISendOTPRepository {

    suspend fun sendOTP(countryCode: String, phoneNumber: String): Results<SendOTPResInterceptor, NetworkError> {
        return try {
            when (val result = sendOTPApiService.sendOTP(countryCode, phoneNumber)) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor()

                    Results.Success(resultInterceptor)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

}