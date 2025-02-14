package com.kompasid.netdatalibrary.core.data.userDetail.model.local

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.base.persistentStorage.SettingsDataSource


class UserDetailDataSource(
    private val settingsDataSource: SettingsDataSource,
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

    fun remove() {
        settingsDataSource.remove(KeySettingsType.ID_GENDER)
        settingsDataSource.remove(KeySettingsType.GENDER)
        settingsDataSource.remove(KeySettingsType.DATE_BIRTH)
        settingsDataSource.remove(KeySettingsType.USER_ID)
        settingsDataSource.remove(KeySettingsType.FIRST_NAME)
        settingsDataSource.remove(KeySettingsType.LAST_NAME)
        settingsDataSource.remove(KeySettingsType.EMAIL)
        settingsDataSource.remove(KeySettingsType.USER_GUID)
        settingsDataSource.remove(KeySettingsType.PHONE_NUMBER)
        settingsDataSource.remove(KeySettingsType.COUNTRY_CODE)
        settingsDataSource.remove(KeySettingsType.COUNTRY)
        settingsDataSource.remove(KeySettingsType.PROVINCE)
        settingsDataSource.remove(KeySettingsType.CITY)
        settingsDataSource.remove(KeySettingsType.IS_ACTIVE)
        settingsDataSource.remove(KeySettingsType.IS_VERIFIED)
        settingsDataSource.remove(KeySettingsType.PHONE_VERIFIED)
    }
}

