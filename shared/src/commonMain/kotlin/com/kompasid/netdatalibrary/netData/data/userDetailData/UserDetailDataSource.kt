package com.kompasid.app.netdatamodule.Example.Data.UserDetailData

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource


class UserDetailDataSource(
    private val settingsDataSource: SettingsDataSource
) {
    fun save(
        userId: String,
        firstName: String,
        lastName: String,
        email: String,
        userGuid: String,
        phoneNumber: String,
        countryCode: String,
        country: String,
        province: String,
        city: String
    ) {
        settingsDataSource.save(KeySettingsType.USER_ID, userId)
        settingsDataSource.save(KeySettingsType.FIRST_NAME, firstName)
        settingsDataSource.save(KeySettingsType.LAST_NAME, lastName)
        settingsDataSource.save(KeySettingsType.EMAIL, email)
        settingsDataSource.save(KeySettingsType.USER_GUID, userGuid)
        settingsDataSource.save(KeySettingsType.PHONE_NUMBER, phoneNumber)
        settingsDataSource.save(KeySettingsType.COUNTRY_CODE, countryCode)
        settingsDataSource.save(KeySettingsType.COUNTRY, country)
        settingsDataSource.save(KeySettingsType.PROVINCE, province)
        settingsDataSource.save(KeySettingsType.CITY, city)
    }
}