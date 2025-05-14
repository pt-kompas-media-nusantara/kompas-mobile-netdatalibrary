package com.kompasid.netdatalibrary.core.data.otp.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.login.dataSource.LoginDataSource
import com.kompasid.netdatalibrary.core.data.otp.dto.interceptor.SendOTPResInterceptor
import com.kompasid.netdatalibrary.core.data.otp.dto.interceptor.VerifyOTPResInterceptor
import com.kompasid.netdatalibrary.core.data.otp.mappers.toInterceptor
import com.kompasid.netdatalibrary.core.data.otp.network.OTPApiService


class OTPRepository(
    private val otpApiService: OTPApiService,
    private val loginDataSource: LoginDataSource,
) : IOTPRepository {

    suspend fun sendOTP(countryCode: String, phoneNumber: String): Results<SendOTPResInterceptor, NetworkError> {
        return try {
            when (val result = otpApiService.sendOTP(countryCode, phoneNumber)) {
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

    suspend fun verifyOTP(countryCode: String, phoneNumber: String, otp: String): Results<VerifyOTPResInterceptor, NetworkError> {
        return try {
            when (val result = otpApiService.verifyOTP(countryCode, phoneNumber, otp)) {
                is ApiResults.Success -> {
                    val resultInterceptor = result.data.toInterceptor()

                    saveLoginData(resultInterceptor)

                    Results.Success(resultInterceptor)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        } catch (exception: Exception) {
            Results.Error(NetworkError.Error(exception))
        }
    }

    private suspend fun saveLoginData(resultInterceptor: VerifyOTPResInterceptor) {
        loginDataSource.save(
            accessToken = resultInterceptor.accessToken,
            refreshToken = resultInterceptor.refreshToken,
            deviceKeyId = resultInterceptor.deviceKeyId,
            isPassEmpty = resultInterceptor.isPassEmpty,
            isSocial = resultInterceptor.isSocial,
        )
    }

}