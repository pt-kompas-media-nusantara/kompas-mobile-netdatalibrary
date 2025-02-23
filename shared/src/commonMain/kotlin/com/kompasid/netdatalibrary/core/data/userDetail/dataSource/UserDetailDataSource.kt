package com.kompasid.netdatalibrary.core.data.userDetail.dataSource

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope



class UserDetailDataSource(
    private val settingsHelper: SettingsHelper
) : IUserDetailDataSource {

    override suspend fun save(data: UserDetailResInterceptor): Unit = coroutineScope {
        listOf(
            async {
                if (settingsHelper.getIntFlow(KeySettingsType.ID_GENDER).value != data.idGender) {
                    settingsHelper.save(KeySettingsType.ID_GENDER, data.idGender)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.GENDER).value != data.gender) {
                    settingsHelper.save(KeySettingsType.GENDER, data.gender)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.DATE_BIRTH).value != data.dateBirth) {
                    settingsHelper.save(KeySettingsType.DATE_BIRTH, data.dateBirth)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.USER_ID).value != data.userId) {
                    settingsHelper.save(KeySettingsType.USER_ID, data.userId)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.FIRST_NAME).value != data.firstName) {
                    settingsHelper.save(KeySettingsType.FIRST_NAME, data.firstName)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.LAST_NAME).value != data.lastName) {
                    settingsHelper.save(KeySettingsType.LAST_NAME, data.lastName)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.EMAIL).value != data.email) {
                    settingsHelper.save(KeySettingsType.EMAIL, data.email)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.USER_GUID).value != data.userGuid) {
                    settingsHelper.save(KeySettingsType.USER_GUID, data.userGuid)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.PHONE_NUMBER).value != data.phoneNumber) {
                    settingsHelper.save(KeySettingsType.PHONE_NUMBER, data.phoneNumber)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.COUNTRY_CODE).value != data.countryCode) {
                    settingsHelper.save(KeySettingsType.COUNTRY_CODE, data.countryCode)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.COUNTRY).value != data.country) {
                    settingsHelper.save(KeySettingsType.COUNTRY, data.country)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.PROVINCE).value != data.province) {
                    settingsHelper.save(KeySettingsType.PROVINCE, data.province)
                }
            },
            async {
                if (settingsHelper.getStringFlow(KeySettingsType.CITY).value != data.city) {
                    settingsHelper.save(KeySettingsType.CITY, data.city)
                }
            },
            async {
                if (settingsHelper.getBooleanFlow(KeySettingsType.IS_ACTIVE).value != data.isActive) {
                    settingsHelper.save(KeySettingsType.IS_ACTIVE, data.isActive)
                }
            },
            async {
                if (settingsHelper.getBooleanFlow(KeySettingsType.IS_VERIFIED).value != data.userStatus.isVerified) {
                    settingsHelper.save(KeySettingsType.IS_VERIFIED, data.userStatus.isVerified)
                }
            },
            async {
                if (settingsHelper.getBooleanFlow(KeySettingsType.PHONE_VERIFIED).value != data.userStatus.phoneVerified) {
                    settingsHelper.save(
                        KeySettingsType.PHONE_VERIFIED,
                        data.userStatus.phoneVerified
                    )
                }
            }
        ).awaitAll()
    }
}
