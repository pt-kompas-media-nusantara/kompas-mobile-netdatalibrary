package com.kompasid.netdatalibrary.core.data.userHistoryMembership.mappers

import com.kompasid.netdatalibrary.core.data.userMembershipHistoryData.dto.OldUserHistoryMembershipResponse
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.HistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipObjResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor

fun OldUserHistoryMembershipResponse.toInterceptor(): UserHistoryMembershipResInterceptor {
    return UserHistoryMembershipResInterceptor(
        user = UserHistoryMembershipObjResInterceptor(
            expired = data?.result?.user?.expired ?: "",
            isActive = data?.result?.user?.isActive ?: "",
            startDate = data?.result?.user?.startDate ?: "",
            endDate = data?.result?.user?.endDate ?: "",
            totalGracePeriod = data?.result?.user?.totalGracePeriod ?: 0,
            gracePeriod = data?.result?.user?.gracePeriod ?: false,
        ),
        active = data?.result?.active?.map {
            HistoryMembershipResInterceptor(
                membershipTitle = it?.membershipTitle ?: "",
                membershipSlug = it?.membershipSlug ?: "",
                startDate = it?.startDate ?: "",
                endDate = it?.endDate ?: ""
            )
        } ?: emptyList(),
        expired = data?.result?.expired?.map {
            HistoryMembershipResInterceptor(
                membershipTitle = it?.membershipTitle ?: "",
                membershipSlug = it?.membershipSlug ?: "",
                startDate = it?.startDate ?: "",
                endDate = it?.endDate ?: ""
            )
        } ?: emptyList()
    )
}