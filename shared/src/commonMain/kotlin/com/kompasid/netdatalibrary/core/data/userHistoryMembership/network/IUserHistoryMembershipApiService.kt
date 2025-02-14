package com.kompasid.netdatalibrary.core.data.userHistoryMembership.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.userMembershipHistoryData.dto.OldUserHistoryMembershipResponse

interface IUserHistoryMembershipApiService {
    suspend fun getUserHistoryMembership(): ApiResults<OldUserHistoryMembershipResponse, NetworkError>
}