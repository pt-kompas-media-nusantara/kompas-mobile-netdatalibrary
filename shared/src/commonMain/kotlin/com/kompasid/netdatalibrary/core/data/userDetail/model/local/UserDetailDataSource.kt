package com.kompasid.netdatalibrary.core.data.userDetail.model.local

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource
import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.UserStatus


class UserDetailDataSource(
    private val settingsDataSource: SettingsDataSource
) {
    fun save(
        idGender: Int,
        gender: String,
        dateBirth: String,
        userId: String,
        firstName: String,
        lastName: String,
        email: String,
        userGuid: String,
        phoneNumber: String,
        countryCode: String,
        country: String,
        province: String,
        city: String,
        isActive: Boolean,
        isVerified: Boolean,
        phoneVerified: Boolean,
    ) {
        settingsDataSource.save(KeySettingsType.ID_GENDER, idGender)
        settingsDataSource.save(KeySettingsType.GENDER, gender)
        settingsDataSource.save(KeySettingsType.DATE_BIRTH, dateBirth)
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
        settingsDataSource.save(KeySettingsType.IS_ACTIVE, isActive)
        settingsDataSource.save(KeySettingsType.IS_VERIFIED, isVerified)
        settingsDataSource.save(KeySettingsType.PHONE_VERIFIED, phoneVerified)

    }
}
