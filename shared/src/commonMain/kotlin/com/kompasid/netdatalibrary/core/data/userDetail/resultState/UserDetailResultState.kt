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
import kotlinx.coroutines.launch


class UserDetailResultState(
    private val settingsHelper: SettingsHelper
) : BaseVM() {

    private val _idGender = MutableStateFlow(0)
    val idGender = _idGender.asStateFlow()

    private val _gender = MutableStateFlow("")
    val gender = _gender.asStateFlow()

    private val _userId = MutableStateFlow("")
    val userId = _userId.asStateFlow()

    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _userGuid = MutableStateFlow("")
    val userGuid = _userGuid.asStateFlow()

    private val _isActive = MutableStateFlow(false)
    val isActive = _isActive.asStateFlow()

    private val _isVerified = MutableStateFlow(false)
    val isVerified = _isVerified.asStateFlow()

    private val _phoneVerified = MutableStateFlow(false)
    val phoneVerified = _phoneVerified.asStateFlow()

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    private val _countryCode = MutableStateFlow("")
    val countryCode = _countryCode.asStateFlow()

    private val _dateBirth = MutableStateFlow("")
    val dateBirth = _dateBirth.asStateFlow()

    private val _country = MutableStateFlow("")
    val country = _country.asStateFlow()

    private val _province = MutableStateFlow("")
    val province = _province.asStateFlow()

    private val _city = MutableStateFlow("")
    val city = _city.asStateFlow()

    init {
        scope.launch {
            _idGender.value = settingsHelper.load(KeySettingsType.ID_GENDER)
            _gender.value = settingsHelper.load(KeySettingsType.GENDER)
            _userId.value = settingsHelper.load(KeySettingsType.USER_ID)
            _firstName.value = settingsHelper.load(KeySettingsType.FIRST_NAME)
            _lastName.value = settingsHelper.load(KeySettingsType.LAST_NAME)
            _email.value = settingsHelper.load(KeySettingsType.EMAIL)
            _userGuid.value = settingsHelper.load(KeySettingsType.USER_GUID)
            _isActive.value = settingsHelper.load(KeySettingsType.IS_ACTIVE)
            _phoneNumber.value = settingsHelper.load(KeySettingsType.PHONE_NUMBER)
            _countryCode.value = settingsHelper.load(KeySettingsType.COUNTRY_CODE)
            _dateBirth.value = settingsHelper.load(KeySettingsType.DATE_BIRTH)
            _country.value = settingsHelper.load(KeySettingsType.COUNTRY)
            _province.value = settingsHelper.load(KeySettingsType.PROVINCE)
            _city.value = settingsHelper.load(KeySettingsType.CITY)
            _isVerified.value = settingsHelper.load(KeySettingsType.IS_VERIFIED)
            _phoneVerified.value = settingsHelper.load(KeySettingsType.PHONE_NUMBER)
        }
    }

    fun updateUserDetails(userDetail: UserDetailResInterceptor) {
        _idGender.value = userDetail.idGender
        _gender.value = userDetail.gender
        _userId.value = userDetail.userId
        _firstName.value = userDetail.firstName
        _lastName.value = userDetail.lastName
        _email.value = userDetail.email
        _userGuid.value = userDetail.userGuid
        _isActive.value = userDetail.isActive
        _isVerified.value = userDetail.userStatus.isVerified
        _phoneVerified.value = userDetail.userStatus.phoneVerified
        _phoneNumber.value = userDetail.phoneNumber
        _countryCode.value = userDetail.countryCode
        _dateBirth.value = userDetail.dateBirth
        _country.value = userDetail.country
        _province.value = userDetail.province
        _city.value = userDetail.city
    }

//    private val userInfoFlow = combine(
//        settingsHelper.getIntFlow(KeySettingsType.ID_GENDER).map { it ?: 0 },
//        settingsHelper.getStringFlow(KeySettingsType.GENDER).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.DATE_BIRTH).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.USER_ID).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.FIRST_NAME).map { it ?: "" },
//    ) { idGender, gender, dateBirth, userId, firstName ->
//        UserDetailResInterceptor(
//            idGender = idGender,
//            gender = gender,
//            dateBirth = dateBirth,
//            userId = userId,
//            firstName = firstName,
//            lastName = "", // Tempatkan default value
//            email = "",
//            userGuid = "",
//            phoneNumber = "",
//            countryCode = "",
//            country = "",
//            province = "",
//            city = "",
//            isActive = false,
//            userStatus = UserStatusInterceptor(isVerified = false, phoneVerified = false)
//        )
//    }
//
//    private val userAdditionalInfoFlow = combine(
//        settingsHelper.getStringFlow(KeySettingsType.LAST_NAME).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.EMAIL).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.USER_GUID).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.PHONE_NUMBER).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.COUNTRY_CODE).map { it ?: "" }
//    ) { lastName, email, userGuid, phoneNumber, countryCode ->
//        UserDetailResInterceptor(
//            idGender = 0,
//            gender = "",
//            dateBirth = "",
//            userId = "",
//            firstName = "",
//            lastName = lastName,
//            email = email,
//            userGuid = userGuid,
//            phoneNumber = phoneNumber,
//            countryCode = countryCode,
//            country = "",
//            province = "",
//            city = "",
//            isActive = false,
//            userStatus = UserStatusInterceptor(isVerified = false, phoneVerified = false)
//        )
//    }
//
//    private val userLocationFlow = combine(
//        settingsHelper.getStringFlow(KeySettingsType.COUNTRY).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.PROVINCE).map { it ?: "" },
//        settingsHelper.getStringFlow(KeySettingsType.CITY).map { it ?: "" }
//    ) { country, province, city ->
//        UserDetailResInterceptor(
//            idGender = 0,
//            gender = "",
//            dateBirth = "",
//            userId = "",
//            firstName = "",
//            lastName = "",
//            email = "",
//            userGuid = "",
//            phoneNumber = "",
//            countryCode = "",
//            country = country,
//            province = province,
//            city = city,
//            isActive = false,
//            userStatus = UserStatusInterceptor(isVerified = false, phoneVerified = false)
//        )
//    }
//
//    private val userStatusFlow = combine(
//        settingsHelper.getBooleanFlow(KeySettingsType.IS_ACTIVE).map { it ?: false },
//        settingsHelper.getBooleanFlow(KeySettingsType.IS_VERIFIED).map { it ?: false },
//        settingsHelper.getBooleanFlow(KeySettingsType.PHONE_VERIFIED).map { it ?: false }
//    ) { isActive, isVerified, phoneVerified ->
//        UserStatusInterceptor(
//            isVerified = isVerified,
//            phoneVerified = phoneVerified
//        ) to isActive
//    }
//
//    val userDetailState: StateFlow<UserDetailResInterceptor> =
//        combine(
//            userInfoFlow,
//            userAdditionalInfoFlow,
//            userLocationFlow,
//            userStatusFlow
//        ) { userInfo, userAdditionalInfo, userLocation, (userStatus, isActive) ->
//            userInfo.copy(
//                lastName = userAdditionalInfo.lastName,
//                email = userAdditionalInfo.email,
//                userGuid = userAdditionalInfo.userGuid,
//                phoneNumber = userAdditionalInfo.phoneNumber,
//                countryCode = userAdditionalInfo.countryCode,
//                country = userLocation.country,
//                province = userLocation.province,
//                city = userLocation.city,
//                isActive = isActive,
//                userStatus = userStatus
//            )
//        }
//            .flowOn(Dispatchers.IO) // Jalankan di thread yang tepat
//            .distinctUntilChanged()
//            .stateIn(
//                scope,
//                SharingStarted.WhileSubscribed(replayExpirationMillis = 9000),
//                UserDetailResInterceptor()
//            )
}
