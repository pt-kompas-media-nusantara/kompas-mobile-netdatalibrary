package com.kompasid.netdatalibrary.core.domain.personalInfo.resultState

import com.kompasid.netdatalibrary.core.data.userDetail.model.local.UserDetailDataSource
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserDetailResInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserDetailState(
    private val userDetailDataSource: UserDetailDataSource
) {
    private val _userDetails = MutableStateFlow<UserDetailResInterceptor?>(null)
    val userDetails: StateFlow<UserDetailResInterceptor?> = _userDetails.asStateFlow()

    fun updateUserDetails(data: UserDetailResInterceptor) {
        _userDetails.value = data

        userDetailDataSource.save(
            data.idGender,
            data.gender,
            data.dateBirth,
            data.userGuid,
            data.firstName,
            data.lastName,
            data.email,
            data.userGuid,
            data.phoneNumber,
            data.countryCode,
            data.country,
            data.province,
            data.city,
            data.isActive,
            data.userStatus.isVerified,
            data.userStatus.phoneVerified,
        )
    }

    fun clearUserDetails() {
        _userDetails.value = null
        userDetailDataSource.remove()
    }
}