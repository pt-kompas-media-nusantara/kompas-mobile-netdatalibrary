package com.kompasid.netdatalibrary.core.data.userHistoryMembership.repository

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor

interface IUserMembershipHistoryRepository {
    suspend fun getUserMembershipHistoryOld(): Results<UserHistoryMembershipResInterceptor, NetworkError>
}