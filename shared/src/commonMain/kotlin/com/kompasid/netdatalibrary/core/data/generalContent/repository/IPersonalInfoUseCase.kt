package com.kompasid.netdatalibrary.core.data.generalContent.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor

interface IPersonalInfoUseCase {
    suspend fun userDetail(): Results<UserDetailResInterceptor, NetworkError>
    suspend fun historyMembersip(): Results<UserHistoryMembershipResInterceptor, NetworkError>
}