package com.kompasid.netdatalibrary.core.data.userDetail.dataSource

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase


class UserDetailDataSource(
    private val settingsUseCase: SettingsUseCase,
) {

    suspend fun save(data: UserDetailResInterceptor) {
        settingsUseCase.save(KeySettingsType.ID_GENDER, data.idGender)
        settingsUseCase.save(KeySettingsType.GENDER, data.gender)
        settingsUseCase.save(KeySettingsType.DATE_BIRTH, data.dateBirth)
        settingsUseCase.save(KeySettingsType.USER_ID, data.userId)
        settingsUseCase.save(KeySettingsType.FIRST_NAME, data.firstName)
        settingsUseCase.save(KeySettingsType.LAST_NAME, data.lastName)
        settingsUseCase.save(KeySettingsType.EMAIL, data.email)
        settingsUseCase.save(KeySettingsType.USER_GUID, data.userGuid)
        settingsUseCase.save(KeySettingsType.PHONE_NUMBER, data.phoneNumber)
        settingsUseCase.save(KeySettingsType.COUNTRY_CODE, data.countryCode)
        settingsUseCase.save(KeySettingsType.COUNTRY, data.country)
        settingsUseCase.save(KeySettingsType.PROVINCE, data.province)
        settingsUseCase.save(KeySettingsType.CITY, data.city)
        settingsUseCase.save(KeySettingsType.IS_ACTIVE, data.isActive)
        settingsUseCase.save(KeySettingsType.IS_VERIFIED, data.userStatus.isVerified)
        settingsUseCase.save(KeySettingsType.PHONE_VERIFIED, data.userStatus.phoneVerified)

    }
}

