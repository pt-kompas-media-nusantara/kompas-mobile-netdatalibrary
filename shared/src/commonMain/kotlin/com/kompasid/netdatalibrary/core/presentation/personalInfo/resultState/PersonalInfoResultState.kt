package com.kompasid.netdatalibrary.core.presentation.personalInfo.resultState

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.core.domain.account.model.StateUserType
import com.kompasid.netdatalibrary.core.presentation.personalInfo.enums.PersonalInfoType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// nurirpppan_ : ini harusnya reactive
//settingsHelper.getStringFlow(KeySettingsType.EXPIRED_MEMBERSHIP) pakai didset untuk langsung set ke property
class PersonalInfoResultState(
    private val settingsHelper: SettingsHelper
) {
//
//    private val _userType = MutableStateFlow<StateUserType>(StateUserType.ANON)
//    var userType: StateFlow<StateUserType> = _userType.asStateFlow()
//
//    private val _email = MutableStateFlow("")
//    val email: StateFlow<String> = _email.asStateFlow()
//
//    private val _stateMembership = MutableStateFlow("")
//    val stateMembership: StateFlow<String> = _stateMembership.asStateFlow()
//
//    private val _firstName = MutableStateFlow("")
//    val firstName: StateFlow<String> = _firstName.asStateFlow()
//
//    private val _lastName = MutableStateFlow("")
//    val lastName: StateFlow<String> = _lastName.asStateFlow()
//
//    private val _dateExpired = MutableStateFlow("")
//    val dateExpired: StateFlow<String> = _dateExpired.asStateFlow()
//
//    private val _username = MutableStateFlow("")
//    val username: StateFlow<String> = _username.asStateFlow()
//
//    private val _city = MutableStateFlow("")
//    val city: StateFlow<String> = _city.asStateFlow()
//
//    private val _country = MutableStateFlow("")
//    val country: StateFlow<String> = _country.asStateFlow()
//
//    private val _dateBirth = MutableStateFlow("")
//    val dateBirth: StateFlow<String> = _dateBirth.asStateFlow()
//
//    private val _idGender = MutableStateFlow(0)
//    val idGender: StateFlow<Int> = _idGender.asStateFlow()
//
//    private val _phoneNumber = MutableStateFlow("")
//    val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()
//
//    private val _province = MutableStateFlow("")
//    val province: StateFlow<String> = _province.asStateFlow()
//
//    suspend fun update(data: List<PersonalInfoType>) {
//        data.forEach { type ->
//            when (type) {
//                PersonalInfoType.EMAIL -> {
//                    val value =
//                        settingsHelper.getStringFlow(KeySettingsType.EMAIL) // kompastesting16@yopmail.com
//                    if (value != _email.value) {
//                        _email.value = value
//                    }
//                }
//
//                PersonalInfoType.ACTIVE_MEMBERSHIP -> {
//                    val value =
//                        settingsHelper.getStringFlow(KeySettingsType.ACTIVE_MEMBERSHIP).value // Aktif Berlangganan
//                    if (value != _stateMembership.value) {
//                        _stateMembership.value = value.toString()
//                    }
//                }
//
//                PersonalInfoType.FIRST_NAME -> {
//                    val value =
//                        settingsHelper.getStringFlow(KeySettingsType.FIRST_NAME).value // Nurirp
//                    if (value != _firstName.value) {
//                        _firstName.value = value.toString()
//                    }
//                }
//
//                PersonalInfoType.LAST_NAME -> {
//                    val value =
//                        settingsHelper.getStringFlow(KeySettingsType.LAST_NAME) // Pangestu
//                    if (value != _lastName.value) {
//                        _lastName.value = value
//                    }
//                }
//
//                PersonalInfoType.EXPIRED_MEMBERSHIP -> {
//                    val value =
//                        settingsHelper.getStringFlow(KeySettingsType.EXPIRED_MEMBERSHIP) // 21 Jan 2022 - 18 Feb 2038
//                    if (value != _dateExpired.value) {
//                        _dateExpired.value = value
//                    }
//                }
//
//                PersonalInfoType.USERNAME -> {
//                    val strings = listOf(firstName.value, lastName.value) // Output: "NP"
//                    val idUserName =
//                        strings.joinToString(separator = "") { it.firstOrNull()?.toString() ?: "" }
//
//                    if (idUserName != _username.value) {
//                        _username.value = idUserName
//                    }
//                }
//            }
//        }
//    }
//
//    suspend fun userType() {
//        update(
//            listOf(
//                PersonalInfoType.EMAIL, PersonalInfoType.ACTIVE_MEMBERSHIP
//            )
//        )
//
//        _userType.value = when {
//            email.value.isEmpty() -> StateUserType.ANON
//            email.value.isNotEmpty() && stateMembership.value == "tidak berlangganan" -> StateUserType.REGON
//            else -> StateUserType.SUBER
//        }
//    }
//
//
//    suspend fun myAccountInformation() {
//        update(
//            listOf(
//                PersonalInfoType.FIRST_NAME,
//                PersonalInfoType.LAST_NAME,
//                PersonalInfoType.EXPIRED_MEMBERSHIP,
//                PersonalInfoType.ACTIVE_MEMBERSHIP,
//                PersonalInfoType.USERNAME,
//            )
//        )
//    }
}

