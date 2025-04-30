package com.kompasid.netdatalibrary.core.data.userHistoryMembership.dataSource

import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserHistoryMembershipResInterceptor
import com.kompasid.netdatalibrary.core.data.userHistoryMembership.model.interceptor.UserMembershipResInterceptor
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class UserMembershipDataSource(
    private val settingsHelper: SettingsHelper
) {

    suspend fun save(data: UserMembershipResInterceptor) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.SUBSCRIPTION_STATUS, data.status),
                settingsHelper.saveAsync(this, KeySettingsType.SUBSCRIPTION_DURATION, data.duration),
                settingsHelper.saveAsync(this, KeySettingsType.SUBSCRIPTION_START_DATE, data.startDate),
                settingsHelper.saveAsync(this, KeySettingsType.SUBSCRIPTION_END_DATE, data.endDate),
                settingsHelper.saveAsync(this, KeySettingsType., data.),
                settingsHelper.saveAsync(this, KeySettingsType., data.),
                settingsHelper.saveAsync(this, KeySettingsType., data.),
                settingsHelper.saveAsync(this, KeySettingsType., data.),
                settingsHelper.saveAsync(this, KeySettingsType., data.),
                settingsHelper.saveAsync(this, KeySettingsType., data.),
                settingsHelper.saveAsync(this, KeySettingsType., data.),
                settingsHelper.saveAsync(this, KeySettingsType., data.),
                settingsHelper.saveAsync(this, KeySettingsType., data.),
                settingsHelper.saveAsync(this, KeySettingsType., data.),
            ).awaitAll()
        }
    }
}



//settingsHelper.saveAsync(this, KeySettingsType., data.),
//settingsHelper.saveAsync(this, KeySettingsType., data.access),
//settingsHelper.saveAsync(this, KeySettingsType., data.gracePeriod),
//settingsHelper.saveAsync(this, KeySettingsType., data.gracePeriodDate),
//settingsHelper.saveAsync(this, KeySettingsType., data.totalGracePeriod),
//settingsHelper.saveAsync(this, KeySettingsType., data.membership),
//settingsHelper.saveAsync(this, KeySettingsType., data.entitlement),
//settingsHelper.saveAsync(this, KeySettingsType., data.guid),
//settingsHelper.saveAsync(this, KeySettingsType., data.email),
//settingsHelper.saveAsync(this, KeySettingsType., data.firstName),
//settingsHelper.saveAsync(this, KeySettingsType., data.lastName),