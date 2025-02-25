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
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class UserDetailResultState(
    private val settingsHelper: SettingsHelper
) : BaseVM() {

    val idGender: StateFlow<Int> = settingsHelper.load(KeySettingsType.ID_GENDER, 0)
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), 0)

    val gender: StateFlow<String> = settingsHelper.load(KeySettingsType.GENDER, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val userId: StateFlow<String> = settingsHelper.load(KeySettingsType.USER_ID, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val firstName: StateFlow<String> = settingsHelper.load(KeySettingsType.FIRST_NAME, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val lastName: StateFlow<String> = settingsHelper.load(KeySettingsType.LAST_NAME, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val email: StateFlow<String> = settingsHelper.load(KeySettingsType.EMAIL, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val userGuid: StateFlow<String> = settingsHelper.load(KeySettingsType.USER_GUID, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val isActive: StateFlow<Boolean> = settingsHelper.load(KeySettingsType.IS_ACTIVE, false)
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), false)

    val isVerified: StateFlow<Boolean> = settingsHelper.load(KeySettingsType.IS_VERIFIED, false)
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), false)

    val phoneVerified: StateFlow<Boolean> =
        settingsHelper.load(KeySettingsType.PHONE_VERIFIED, false)
            .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), false)

    val phoneNumber: StateFlow<String> = settingsHelper.load(KeySettingsType.PHONE_NUMBER, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val countryCode: StateFlow<String> = settingsHelper.load(KeySettingsType.COUNTRY_CODE, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val dateBirth: StateFlow<String> = settingsHelper.load(KeySettingsType.DATE_BIRTH, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val country: StateFlow<String> = settingsHelper.load(KeySettingsType.COUNTRY, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val province: StateFlow<String> = settingsHelper.load(KeySettingsType.PROVINCE, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

    val city: StateFlow<String> = settingsHelper.load(KeySettingsType.CITY, "")
        .distinctUntilChanged()
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), "")

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
    val data: StateFlow<UserDetailResInterceptor> = combine(
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
    }
        .distinctUntilChanged()
        .debounce(300)
        .stateIn(scope, SharingStarted.WhileSubscribed(5000), UserDetailResInterceptor())



}
