package com.kompasid.netdatalibrary.core.data.userHistoryMembership.mappers

import com.kompasid.netdatalibrary.core.data.userMembershipHistoryData.dto.OldUserHistoryMembershipResponse
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.HistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipObjResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor

fun OldUserHistoryMembershipResponse.toInterceptor(): UserHistoryMembershipResInterceptor {
    return UserHistoryMembershipResInterceptor(
        user = UserHistoryMembershipObjResInterceptor(
            expired = result?.user?.expired ?: "",
            isActive = result?.user?.isActive ?: "",
            startDate = result?.user?.startDate ?: "",
            endDate = result?.user?.endDate ?: "",
            totalGracePeriod = result?.user?.totalGracePeriod ?: 0,
            gracePeriod = result?.user?.gracePeriod ?: false,
        ),
        active = result?.active?.map {
            HistoryMembershipResInterceptor(
                membershipTitle = it.membershipTitle ?: "",
                membershipSlug = it.membershipSlug ?: "",
                startDate = it.startDate ?: "",
                endDate = it.endDate ?: ""
            )
        } ?: emptyList(),
        expired = result?.expired?.map {
            HistoryMembershipResInterceptor(
                membershipTitle = it.membershipTitle ?: "",
                membershipSlug = it.membershipSlug ?: "",
                startDate = it.startDate ?: "",
                endDate = it.endDate ?: ""
            )
        } ?: emptyList()
    )
}