package com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserHistoryMembershipResInterceptor

interface IUserMembershipHistoryRepository {
    suspend fun getUserMembershipHistory(): Results<UserHistoryMembershipResInterceptor, NetworkError>
}