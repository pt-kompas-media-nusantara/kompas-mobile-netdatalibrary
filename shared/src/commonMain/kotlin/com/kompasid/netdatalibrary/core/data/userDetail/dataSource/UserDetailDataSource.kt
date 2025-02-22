package com.kompasid.netdatalibrary.core.data.userDetail.dataSource

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


class UserDetailDataSource(
    private val settingsUseCase: SettingsUseCase
) {

    suspend fun save(data: UserDetailResInterceptor) = coroutineScope {
        listOf(
            async {
                if (settingsUseCase.getInt(KeySettingsType.ID_GENDER) != data.idGender) {
                    settingsUseCase.save(KeySettingsType.ID_GENDER, data.idGender)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.GENDER) != data.gender) {
                    settingsUseCase.save(KeySettingsType.GENDER, data.gender)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.DATE_BIRTH) != data.dateBirth) {
                    settingsUseCase.save(KeySettingsType.DATE_BIRTH, data.dateBirth)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.USER_ID) != data.userId) {
                    settingsUseCase.save(KeySettingsType.USER_ID, data.userId)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.FIRST_NAME) != data.firstName) {
                    settingsUseCase.save(KeySettingsType.FIRST_NAME, data.firstName)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.LAST_NAME) != data.lastName) {
                    settingsUseCase.save(KeySettingsType.LAST_NAME, data.lastName)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.EMAIL) != data.email) {
                    settingsUseCase.save(KeySettingsType.EMAIL, data.email)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.USER_GUID) != data.userGuid) {
                    settingsUseCase.save(KeySettingsType.USER_GUID, data.userGuid)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.PHONE_NUMBER) != data.phoneNumber) {
                    settingsUseCase.save(KeySettingsType.PHONE_NUMBER, data.phoneNumber)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.COUNTRY_CODE) != data.countryCode) {
                    settingsUseCase.save(KeySettingsType.COUNTRY_CODE, data.countryCode)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.COUNTRY) != data.country) {
                    settingsUseCase.save(KeySettingsType.COUNTRY, data.country)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.PROVINCE) != data.province) {
                    settingsUseCase.save(KeySettingsType.PROVINCE, data.province)
                }
            },
            async {
                if (settingsUseCase.getString(KeySettingsType.CITY) != data.city) {
                    settingsUseCase.save(KeySettingsType.CITY, data.city)
                }
            },
            async {
                if (settingsUseCase.getBoolean(KeySettingsType.IS_ACTIVE) != data.isActive) {
                    settingsUseCase.save(KeySettingsType.IS_ACTIVE, data.isActive)
                }
            },
            async {
                if (settingsUseCase.getBoolean(KeySettingsType.IS_VERIFIED) != data.userStatus.isVerified) {
                    settingsUseCase.save(KeySettingsType.IS_VERIFIED, data.userStatus.isVerified)
                }
            },
            async {
                if (settingsUseCase.getBoolean(KeySettingsType.PHONE_VERIFIED) != data.userStatus.phoneVerified) {
                    settingsUseCase.save(
                        KeySettingsType.PHONE_VERIFIED,
                        data.userStatus.phoneVerified
                    )
                }
            }
        ).awaitAll()
    }
}
