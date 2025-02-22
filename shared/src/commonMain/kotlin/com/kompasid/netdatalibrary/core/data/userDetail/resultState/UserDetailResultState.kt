package com.kompasid.netdatalibrary.core.data.userDetail.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserStatusInterceptor
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class UserDetailResultState(
    private val settingsHelper: SettingsHelper
) : BaseVM() {

    private val userInfoFlow = combine(
        settingsHelper.getIntFlow(KeySettingsType.ID_GENDER).map { it ?: 0 },
        settingsHelper.getStringFlow(KeySettingsType.GENDER).map { it ?: "" },
        settingsHelper.getStringFlow(KeySettingsType.DATE_BIRTH).map { it ?: "" },
        settingsHelper.getStringFlow(KeySettingsType.USER_ID).map { it ?: "" },
        settingsHelper.getStringFlow(KeySettingsType.FIRST_NAME).map { it ?: "" },
    ) { idGender, gender, dateBirth, userId, firstName ->
        UserDetailResInterceptor(
            idGender = idGender,
            gender = gender,
            dateBirth = dateBirth,
            userId = userId,
            firstName = firstName,
            lastName = "", // Tempatkan default value
            email = "",
            userGuid = "",
            phoneNumber = "",
            countryCode = "",
            country = "",
            province = "",
            city = "",
            isActive = false,
            userStatus = UserStatusInterceptor(isVerified = false, phoneVerified = false)
        )
    }

    private val userAdditionalInfoFlow = combine(
        settingsHelper.getStringFlow(KeySettingsType.LAST_NAME).map { it ?: "" },
        settingsHelper.getStringFlow(KeySettingsType.EMAIL).map { it ?: "" },
        settingsHelper.getStringFlow(KeySettingsType.USER_GUID).map { it ?: "" },
        settingsHelper.getStringFlow(KeySettingsType.PHONE_NUMBER).map { it ?: "" },
        settingsHelper.getStringFlow(KeySettingsType.COUNTRY_CODE).map { it ?: "" }
    ) { lastName, email, userGuid, phoneNumber, countryCode ->
        UserDetailResInterceptor(
            idGender = 0,
            gender = "",
            dateBirth = "",
            userId = "",
            firstName = "",
            lastName = lastName,
            email = email,
            userGuid = userGuid,
            phoneNumber = phoneNumber,
            countryCode = countryCode,
            country = "",
            province = "",
            city = "",
            isActive = false,
            userStatus = UserStatusInterceptor(isVerified = false, phoneVerified = false)
        )
    }

    private val userLocationFlow = combine(
        settingsHelper.getStringFlow(KeySettingsType.COUNTRY).map { it ?: "" },
        settingsHelper.getStringFlow(KeySettingsType.PROVINCE).map { it ?: "" },
        settingsHelper.getStringFlow(KeySettingsType.CITY).map { it ?: "" }
    ) { country, province, city ->
        UserDetailResInterceptor(
            idGender = 0,
            gender = "",
            dateBirth = "",
            userId = "",
            firstName = "",
            lastName = "",
            email = "",
            userGuid = "",
            phoneNumber = "",
            countryCode = "",
            country = country,
            province = province,
            city = city,
            isActive = false,
            userStatus = UserStatusInterceptor(isVerified = false, phoneVerified = false)
        )
    }

    private val userStatusFlow = combine(
        settingsHelper.getBooleanFlow(KeySettingsType.IS_ACTIVE).map { it ?: false },
        settingsHelper.getBooleanFlow(KeySettingsType.IS_VERIFIED).map { it ?: false },
        settingsHelper.getBooleanFlow(KeySettingsType.PHONE_VERIFIED).map { it ?: false }
    ) { isActive, isVerified, phoneVerified ->
        UserStatusInterceptor(
            isVerified = isVerified,
            phoneVerified = phoneVerified
        ) to isActive
    }

    val userDetailState: StateFlow<UserDetailResInterceptor> =
        combine(
            userInfoFlow,
            userAdditionalInfoFlow,
            userLocationFlow,
            userStatusFlow
        ) { userInfo, userAdditionalInfo, userLocation, (userStatus, isActive) ->
            userInfo.copy(
                lastName = userAdditionalInfo.lastName,
                email = userAdditionalInfo.email,
                userGuid = userAdditionalInfo.userGuid,
                phoneNumber = userAdditionalInfo.phoneNumber,
                countryCode = userAdditionalInfo.countryCode,
                country = userLocation.country,
                province = userLocation.province,
                city = userLocation.city,
                isActive = isActive,
                userStatus = userStatus
            )
        }
            .flowOn(Dispatchers.IO) // Jalankan di thread yang tepat
            .distinctUntilChanged()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
                UserDetailResInterceptor()
            )
}
