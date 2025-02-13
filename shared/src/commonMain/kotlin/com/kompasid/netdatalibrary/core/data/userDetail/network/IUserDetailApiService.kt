package com.kompasid.netdatalibrary.core.data.userDetail.network

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.core.data.userDetailData.dto.OldUserDetailResponse

interface IUserDetailApiService {
    suspend fun getUserDetailOld(): ApiResults<OldUserDetailResponse, NetworkError>
}