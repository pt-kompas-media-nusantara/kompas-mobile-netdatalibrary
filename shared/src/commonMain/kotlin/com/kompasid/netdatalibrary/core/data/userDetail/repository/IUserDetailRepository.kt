package com.kompasid.netdatalibrary.core.data.userDetail.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserDetailResInterceptor

interface IUserDetailRepository {
    suspend fun getUserDetailOld(): Results<UserDetailResInterceptor, NetworkError>
}