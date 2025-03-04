package com.kompasid.netdatalibrary.core.data.userDetail.dataSource

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

    override suspend fun save(data: UserDetailResInterceptor): Unit = supervisorScope {
        listOf(
            KeySettingsType.ID_GENDER to data.idGender,
            KeySettingsType.GENDER to data.gender,
            KeySettingsType.DATE_BIRTH to data.dateBirth,
            KeySettingsType.USER_ID to data.userId,
            KeySettingsType.FIRST_NAME to data.firstName,
            KeySettingsType.LAST_NAME to data.lastName,
            KeySettingsType.EMAIL to data.email,
            KeySettingsType.USER_GUID to data.userGuid,
            KeySettingsType.PHONE_NUMBER to data.phoneNumber,
            KeySettingsType.COUNTRY_CODE to data.countryCode,
            KeySettingsType.COUNTRY to data.country,
            KeySettingsType.PROVINCE to data.province,
            KeySettingsType.CITY to data.city,
            KeySettingsType.IS_ACTIVE to data.isActive,
            KeySettingsType.IS_VERIFIED to data.userStatus.isVerified,
            KeySettingsType.PHONE_VERIFIED to data.userStatus.phoneVerified
        ).forEach { (key, value) ->
            launch { runCatching { settingsHelper.save(key, value) } }
        }
    }

}
