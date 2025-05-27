package com.kompasid.netdatalibrary.core.domain.settings.useCase

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

class SettingsUseCase(
    private val settings: SettingsHelper
) {
    // ===== for api
    @NativeCoroutines
    fun observeAccessToken(): Flow<String> =
        settings.load(KeySettingsType.ACCESS_TOKEN, "")

    @NativeCoroutines
    suspend fun getAccessToken(): String =
        settings.get(KeySettingsType.ACCESS_TOKEN, "")

    @NativeCoroutines
    fun observeRefreshToken(): Flow<String> =
        settings.load(KeySettingsType.REFRESH_TOKEN, "")

    @NativeCoroutines
    suspend fun getRefreshToken(): String =
        settings.get(KeySettingsType.REFRESH_TOKEN, "")


    // ===== personal information user

    @NativeCoroutines
    fun observeIsVerified(): Flow<Boolean> =
        settings.load(KeySettingsType.IS_VERIFIED, false)

    @NativeCoroutines
    suspend fun getIsVerified(): Boolean =
        settings.get(KeySettingsType.IS_VERIFIED, false)

//    @NativeCoroutines
//    fun observeSSO(): Flow<String> =
//        settings.load(KeySettingsType.SSO, "")
//
//    @NativeCoroutines
//    suspend fun getSSO(): String =
//        settings.get(KeySettingsType.SSO, "")

    @NativeCoroutines
    fun observeIdGender(): Flow<Int> =
        settings.load(KeySettingsType.ID_GENDER, 0)

    @NativeCoroutines
    suspend fun getIdGender(): Int =
        settings.get(KeySettingsType.ID_GENDER, 0)

    @NativeCoroutines
    fun observeGender(): Flow<String> =
        settings.load(KeySettingsType.GENDER, "")

    @NativeCoroutines
    suspend fun getGender(): String =
        settings.get(KeySettingsType.GENDER, "")

    @NativeCoroutines
    fun observeDateBirth(): Flow<String> =
        settings.load(KeySettingsType.DATE_BIRTH, "")

    @NativeCoroutines
    suspend fun getDateBirth(): String =
        settings.get(KeySettingsType.DATE_BIRTH, "")

    @NativeCoroutines
    fun observeFirstName(): Flow<String> =
        settings.load(KeySettingsType.FIRST_NAME, "")

    @NativeCoroutines
    suspend fun getFirstName(): String =
        settings.get(KeySettingsType.FIRST_NAME, "")

    @NativeCoroutines
    fun observeLastName(): Flow<String> =
        settings.load(KeySettingsType.LAST_NAME, "")

    @NativeCoroutines
    suspend fun getLastName(): String =
        settings.get(KeySettingsType.LAST_NAME, "")

    @NativeCoroutines
    fun observeEmail(): Flow<String> =
        settings.load(KeySettingsType.EMAIL, "")

    @NativeCoroutines
    suspend fun getEmail(): String =
        settings.get(KeySettingsType.EMAIL, "")

//    @NativeCoroutines
//    fun observeLoginType(): Flow<String> =
//        settings.load(KeySettingsType.LOGIN_TYPE, "")
//
//    @NativeCoroutines
//    suspend fun getLoginType(): String =
//        settings.get(KeySettingsType.LOGIN_TYPE, "")

    @NativeCoroutines
    fun observeGuid(): Flow<String> =
        settings.load(KeySettingsType.USER_GUID, "")

    @NativeCoroutines
    suspend fun getGuid(): String =
        settings.get(KeySettingsType.USER_GUID, "")

    @NativeCoroutines
    fun observePhoneNumber(): Flow<String> =
        settings.load(KeySettingsType.PHONE_NUMBER, "")

    @NativeCoroutines
    suspend fun getPhoneNumber(): String =
        settings.get(KeySettingsType.PHONE_NUMBER, "")

    @NativeCoroutines
    fun observeCountryCode(): Flow<String> =
        settings.load(KeySettingsType.COUNTRY_CODE, "")

    @NativeCoroutines
    suspend fun getCountryCode(): String =
        settings.get(KeySettingsType.COUNTRY_CODE, "")

    @NativeCoroutines
    fun observeCountry(): Flow<String> =
        settings.load(KeySettingsType.COUNTRY, "")

    @NativeCoroutines
    suspend fun getCountry(): String =
        settings.get(KeySettingsType.COUNTRY, "")

    @NativeCoroutines
    fun observeProvince(): Flow<String> =
        settings.load(KeySettingsType.PROVINCE, "")

    @NativeCoroutines
    suspend fun getProvince(): String =
        settings.get(KeySettingsType.PROVINCE, "")

    @NativeCoroutines
    fun observeCity(): Flow<String> =
        settings.load(KeySettingsType.CITY, "")

    @NativeCoroutines
    suspend fun getCity(): String =
        settings.get(KeySettingsType.CITY, "")

    @NativeCoroutines
    fun observeUserName(): Flow<String> =
        settings.load(KeySettingsType.USERNAME, "")

    @NativeCoroutines
    suspend fun getUserName(): String =
        settings.get(KeySettingsType.USERNAME, "")


    // ===== for api
    @NativeCoroutines
    fun observeDeviceKeyId(): Flow<String> =
        settings.load(KeySettingsType.DEVICE_KEY_ID, "")

    @NativeCoroutines
    suspend fun getDeviceKeyId(): String =
        settings.get(KeySettingsType.DEVICE_KEY_ID, "")
}


