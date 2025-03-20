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

    override suspend fun save(data: UserDetailResInterceptor): Unit = coroutineScope {
        val tasks = listOf(
            async { settingsHelper.save(KeySettingsType.ID_GENDER, data.idGender) },
            async { settingsHelper.save(KeySettingsType.GENDER, data.gender) },
            async { settingsHelper.save(KeySettingsType.DATE_BIRTH, data.dateBirth) },
            async { settingsHelper.save(KeySettingsType.USER_ID, data.userId) },
            async { settingsHelper.save(KeySettingsType.FIRST_NAME, data.firstName) },
            async { settingsHelper.save(KeySettingsType.LAST_NAME, data.lastName) },
            async { settingsHelper.save(KeySettingsType.EMAIL, data.email) },
            async { settingsHelper.save(KeySettingsType.USER_GUID, data.userGuid) },
            async { settingsHelper.save(KeySettingsType.PHONE_NUMBER, data.phoneNumber) },
            async { settingsHelper.save(KeySettingsType.COUNTRY_CODE, data.countryCode) },
            async { settingsHelper.save(KeySettingsType.COUNTRY, data.country) },
            async { settingsHelper.save(KeySettingsType.PROVINCE, data.province) },
            async { settingsHelper.save(KeySettingsType.CITY, data.city) },
            async { settingsHelper.save(KeySettingsType.IS_ACTIVE, data.isActive) },
            async { settingsHelper.save(KeySettingsType.IS_VERIFIED, data.userStatus.isVerified) },
            async { settingsHelper.save(KeySettingsType.PHONE_VERIFIED, data.userStatus.phoneVerified) }
        )

        try {
            tasks.awaitAll()
        } catch (e: Exception) {
            throw Exception("Error ", e)
        }
    }
}
