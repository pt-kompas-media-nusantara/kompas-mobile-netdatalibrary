package com.kompasid.netdatalibrary.core.presentation.state.personalInfo

import com.kompasid.netdatalibrary.base.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.core.data.myRubriks.dto.interceptor.MyRubriksResInterceptor
import com.kompasid.netdatalibrary.core.domain.account.model.MyAccountInformationModel
import com.kompasid.netdatalibrary.core.domain.account.model.StateUserType
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// nurirpppan_ : ini harusnya reactive
//settingsUseCase.getStringDataSource(KeySettingsType.EXPIRED_MEMBERSHIP) pakai didset untuk langsung set ke property
class PersonalInfoResultState(
    private val settingsUseCase: SettingsUseCase
) {

    private val _userType = MutableStateFlow<StateUserType>(StateUserType.ANON)
    var userType: StateFlow<StateUserType> = _userType.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _stateMembership = MutableStateFlow("")
    val stateMembership: StateFlow<String> = _stateMembership.asStateFlow()

    private val _firstName = MutableStateFlow("")
    val firstName: StateFlow<String> = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName: StateFlow<String> = _lastName.asStateFlow()

    private val _dateExpired = MutableStateFlow("")
    val dateExpired: StateFlow<String> = _dateExpired.asStateFlow()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city.asStateFlow()

    private val _country = MutableStateFlow("")
    val country: StateFlow<String> = _country.asStateFlow()

    private val _dateBirth = MutableStateFlow("")
    val dateBirth: StateFlow<String> = _dateBirth.asStateFlow()

    private val _idGender = MutableStateFlow(0)
    val idGender: StateFlow<Int> = _idGender.asStateFlow()

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    private val _province = MutableStateFlow("")
    val province: StateFlow<String> = _province.asStateFlow()

    suspend fun update(data: List<PersonalInfoType>) {
        data.forEach { type ->
            when (type) {
                PersonalInfoType.EMAIL -> {
                    val value =
                        settingsUseCase.getStringDataSource(KeySettingsType.EMAIL) // kompastesting16@yopmail.com
                    if (value != _email.value) {
                        _email.value = value
                    }
                }

                PersonalInfoType.ACTIVE_MEMBERSHIP -> {
                    val value =
                        settingsUseCase.getStringDataSource(KeySettingsType.ACTIVE_MEMBERSHIP) // Aktif Berlangganan
                    if (value != _stateMembership.value) {
                        _stateMembership.value = value
                    }
                }

                PersonalInfoType.FIRST_NAME -> {
                    val value =
                        settingsUseCase.getStringDataSource(KeySettingsType.FIRST_NAME) // Nurirp
                    if (value != _firstName.value) {
                        _firstName.value = value
                    }
                }

                PersonalInfoType.LAST_NAME -> {
                    val value =
                        settingsUseCase.getStringDataSource(KeySettingsType.LAST_NAME) // Pangestu
                    if (value != _lastName.value) {
                        _lastName.value = value
                    }
                }

                PersonalInfoType.EXPIRED_MEMBERSHIP -> {
                    val value =
                        settingsUseCase.getStringDataSource(KeySettingsType.EXPIRED_MEMBERSHIP) // 21 Jan 2022 - 18 Feb 2038
                    if (value != _dateExpired.value) {
                        _dateExpired.value = value
                    }
                }

                PersonalInfoType.USERNAME -> {
                    val strings = listOf(firstName.value, lastName.value) // Output: "NP"
                    val idUserName =
                        strings.joinToString(separator = "") { it.firstOrNull()?.toString() ?: "" }

                    if (idUserName != _username.value) {
                        _username.value = idUserName
                    }
                }
            }
        }
    }

    suspend fun userType() {
        update(
            listOf(
                PersonalInfoType.EMAIL, PersonalInfoType.ACTIVE_MEMBERSHIP
            )
        )

        _userType.value = when {
            email.value.isEmpty() -> StateUserType.ANON
            email.value.isNotEmpty() && stateMembership.value == "tidak berlangganan" -> StateUserType.REGON
            else -> StateUserType.SUBER
        }
    }


    suspend fun myAccountInformation() {
        update(
            listOf(
                PersonalInfoType.FIRST_NAME,
                PersonalInfoType.LAST_NAME,
                PersonalInfoType.EXPIRED_MEMBERSHIP,
                PersonalInfoType.ACTIVE_MEMBERSHIP,
                PersonalInfoType.USERNAME,
            )
        )
    }
}

class DeviceInfoResultState(
    private val settingsUseCase: SettingsUseCase
) {
    private var _originalIdTransaksi = MutableStateFlow("")
    val originalIdTransaksi: StateFlow<String> = _originalIdTransaksi.asStateFlow()

    private var _idTransaksi = MutableStateFlow("")
    val idTransaksi: StateFlow<String> = _idTransaksi.asStateFlow()

    private var _deviceType = MutableStateFlow("")
    val deviceType: StateFlow<String> = _deviceType.asStateFlow()

    private var _osVersion = MutableStateFlow("")
    val osVersion: StateFlow<String> = _osVersion.asStateFlow()

    private var _currentAppVersion = MutableStateFlow("")
    val currentAppVersion: StateFlow<String> = _currentAppVersion.asStateFlow()

    private var _newAppVersion = MutableStateFlow("")
    val newAppVersion: StateFlow<String> = _newAppVersion.asStateFlow()

    private var _historyTransaction = MutableStateFlow("")
    val historyTransaction: StateFlow<String> = _historyTransaction.asStateFlow()
}