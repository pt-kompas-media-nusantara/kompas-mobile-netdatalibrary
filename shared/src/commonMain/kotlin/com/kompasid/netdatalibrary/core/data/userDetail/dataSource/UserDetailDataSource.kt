package com.kompasid.netdatalibrary.core.data.userDetail.dataSource

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor


class UserDetailDataSource(
    private val settingsDataSource: SettingsDataSource,
) {

    fun save(data: UserDetailResInterceptor) {
        settingsDataSource.save(KeySettingsType.ID_GENDER, data.idGender)
        settingsDataSource.save(KeySettingsType.GENDER, data.gender)
        settingsDataSource.save(KeySettingsType.DATE_BIRTH, data.dateBirth)
        settingsDataSource.save(KeySettingsType.USER_ID, data.userId)
        settingsDataSource.save(KeySettingsType.FIRST_NAME, data.firstName)
        settingsDataSource.save(KeySettingsType.LAST_NAME, data.lastName)
        settingsDataSource.save(KeySettingsType.EMAIL, data.email)
        settingsDataSource.save(KeySettingsType.USER_GUID, data.userGuid)
        settingsDataSource.save(KeySettingsType.PHONE_NUMBER, data.phoneNumber)
        settingsDataSource.save(KeySettingsType.COUNTRY_CODE, data.countryCode)
        settingsDataSource.save(KeySettingsType.COUNTRY, data.country)
        settingsDataSource.save(KeySettingsType.PROVINCE, data.province)
        settingsDataSource.save(KeySettingsType.CITY, data.city)
        settingsDataSource.save(KeySettingsType.IS_ACTIVE, data.isActive)
        settingsDataSource.save(KeySettingsType.IS_VERIFIED, data.userStatus.isVerified)
        settingsDataSource.save(KeySettingsType.PHONE_VERIFIED, data.userStatus.phoneVerified)

    }
}

