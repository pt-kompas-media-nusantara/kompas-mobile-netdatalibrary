package com.kompasid.netdatalibrary.core.data.userDetail.dataSource

import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope


class UserDetailDataSource(
    private val settingsHelper: SettingsHelper
) : IUserDetailDataSource {

    suspend fun save(data: UserDetailResInterceptor) {
        coroutineScope {
            listOf(
                settingsHelper.saveAsync(this, KeySettingsType.FIRST_NAME, data.firstName),
                settingsHelper.saveAsync(this, KeySettingsType.LAST_NAME, data.lastName),
                settingsHelper.saveAsync(this, KeySettingsType.EMAIL, data.email),
                settingsHelper.saveAsync(this, KeySettingsType.USER_GUID, data.userGuid),
                settingsHelper.saveAsync(this, KeySettingsType.IS_VERIFIED, data.userStatus.isVerified),
                settingsHelper.saveAsync(this, KeySettingsType.PHONE_VERIFIED, data.userStatus.phoneVerified),
                settingsHelper.saveAsync(this, KeySettingsType.PHONE_NUMBER, data.phoneNumber),
                settingsHelper.saveAsync(this, KeySettingsType.COUNTRY_CODE, data.countryCode),
                settingsHelper.saveAsync(this, KeySettingsType.DATE_BIRTH, data.dateBirth),
                settingsHelper.saveAsync(this, KeySettingsType.COUNTRY, data.country),
                settingsHelper.saveAsync(this, KeySettingsType.ID_GENDER, data.idGender),
                settingsHelper.saveAsync(this, KeySettingsType.GENDER, data.gender),
                settingsHelper.saveAsync(this, KeySettingsType.PROVINCE, data.province),
                settingsHelper.saveAsync(this, KeySettingsType.CITY, data.city),
            ).awaitAll()
        }
    }
}
