package com.kompasid.netdatalibrary.core.data.userDetail.resultState

import com.kompasid.netdatalibrary.BaseVM
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserDetailResInterceptor
import com.kompasid.netdatalibrary.core.data.userDetail.dto.interceptor.UserStatusInterceptor
import com.kompasid.netdatalibrary.core.presentation.launchApp.model.DeviceInfoState
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class UserDetailResultState(
    private val settingsHelper: SettingsHelper
) : BaseVM() {

    val idGender: StateFlow<Int> = settingsHelper.load(KeySettingsType.ID_GENDER, 0)
        .stateIn(scope, SharingStarted.Lazily, 0)

    val gender: StateFlow<String> = settingsHelper.load(KeySettingsType.GENDER, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val userId: StateFlow<String> = settingsHelper.load(KeySettingsType.USER_ID, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val firstName: StateFlow<String> = settingsHelper.load(KeySettingsType.FIRST_NAME, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val lastName: StateFlow<String> = settingsHelper.load(KeySettingsType.LAST_NAME, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val email: StateFlow<String> = settingsHelper.load(KeySettingsType.EMAIL, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val userGuid: StateFlow<String> = settingsHelper.load(KeySettingsType.USER_GUID, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val isActive: StateFlow<Boolean> = settingsHelper.load(KeySettingsType.IS_ACTIVE, false)
        .stateIn(scope, SharingStarted.Lazily, false)

    val isVerified: StateFlow<Boolean> = settingsHelper.load(KeySettingsType.IS_VERIFIED, false)
        .stateIn(scope, SharingStarted.Lazily, false)

    val phoneVerified: StateFlow<Boolean> =
        settingsHelper.load(KeySettingsType.PHONE_VERIFIED, false)
            .stateIn(scope, SharingStarted.Lazily, false)

    val phoneNumber: StateFlow<String> = settingsHelper.load(KeySettingsType.PHONE_NUMBER, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val countryCode: StateFlow<String> = settingsHelper.load(KeySettingsType.COUNTRY_CODE, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val dateBirth: StateFlow<String> = settingsHelper.load(KeySettingsType.DATE_BIRTH, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val country: StateFlow<String> = settingsHelper.load(KeySettingsType.COUNTRY, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val province: StateFlow<String> = settingsHelper.load(KeySettingsType.PROVINCE, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    val city: StateFlow<String> = settingsHelper.load(KeySettingsType.CITY, "")
        .stateIn(scope, SharingStarted.Lazily, "")

    private val combinePart1 = combine(
        idGender, gender, userId, firstName, lastName
    ) { idGender, gender, userId, firstName, lastName ->
        UserDetailResInterceptor(
            idGender = idGender,
            gender = gender,
            userId = userId,
            firstName = firstName,
            lastName = lastName
        )
    }

    private val combinePart2 = combine(
        email, userGuid, isActive, isVerified, phoneVerified
    ) { email, userGuid, isActive, isVerified, phoneVerified ->
        UserDetailResInterceptor(
            email = email,
            userGuid = userGuid,
            isActive = isActive,
            userStatus = UserStatusInterceptor(isVerified, phoneVerified)
        )
    }

    private val combinePart3 = combine(
        phoneNumber, countryCode, dateBirth, country
    ) { phoneNumber, countryCode, dateBirth, country ->
        UserDetailResInterceptor(
            phoneNumber = phoneNumber,
            countryCode = countryCode,
            dateBirth = dateBirth,
            country = country,

            )
    }

    private val combinePart4 = combine(
        province,
        city
    ) { province, city ->
        UserDetailResInterceptor(
            province = province,
            city = city
        )
    }

    // 🔹 Gabungkan semua hasil menjadi satu `UserDetailResInterceptor`
    val userDetail: StateFlow<UserDetailResInterceptor> = combine(
        combinePart1, combinePart2, combinePart3, combinePart4
    ) { part1, part2, part3, part4 ->
        part1.copy(
            email = part2.email,
            userGuid = part2.userGuid,
            isActive = part2.isActive,
            userStatus = part2.userStatus,
            phoneNumber = part3.phoneNumber,
            countryCode = part3.countryCode,
            dateBirth = part3.dateBirth,
            country = part3.country,
            province = part4.province,
            city = part4.city
        )
    }.stateIn(scope, SharingStarted.Lazily, UserDetailResInterceptor())


}
