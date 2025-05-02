package com.kompasid.netdatalibrary.core.data.otp.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.otp.dto.interceptor.SendOTPResInterceptor
import com.kompasid.netdatalibrary.core.data.otp.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.otp.network.OTPApiService


class SendOTPRepository(
    private val OTPApiService: OTPApiService,
) : ISendOTPRepository {

    suspend fun sendOTP(countryCode: String, phoneNumber: String): Results<SendOTPResInterceptor, NetworkError> {
        return try {
            when (val result = OTPApiService.sendOTP(countryCode, phoneNumber)) {
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