package com.kompasid.netdatalibrary.core.data.generalContent.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserHistoryMembershipResInterceptor

interface IPersonalInfoUseCase {
    suspend fun userDetail(): Results<UserDetailResInterceptor, NetworkError>
    suspend fun historyMembersip(): Results<UserHistoryMembershipResInterceptor, NetworkError>
}