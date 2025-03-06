package com.kompasid.netdatalibrary.core.domain.personalInfo.resultState

import com.kompasid.netdatalibrary.core.domain.personalInfo.interceptor.PersonalInfoInterceptor
import com.kompasid.netdatalibrary.core.domain.personalInfo.other.UpdateProfileType
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PersonalInfoState {
    private val _state: MutableStateFlow<PersonalInfoInterceptor> =
        MutableStateFlow(PersonalInfoInterceptor())

    private val state: StateFlow<PersonalInfoInterceptor> = _state.asStateFlow()

    fun streamPersonalInfo(): Flow<PersonalInfoInterceptor> = channelFlow {
        val job = launch {
            state.collectLatest { newData ->
                send(newData)
            }
        }
        awaitClose { job.cancel() } // Menutup stream saat tidak digunakan
    }

    suspend fun updatePersonalInfo(newData: PersonalInfoInterceptor) {
        _state.value = newData
    }


}

//tidak di pakai, karna setelah update profile selanjutnya hit api user detail
//suspend fun updateProfile(type: UpdateProfileType) {
//    when (type) {
//        is UpdateProfileType.DateBirth -> {
//            settingsHelper.save(KeySettingsType.DATE_BIRTH, type.dateBirth)
//        }
//
//        is UpdateProfileType.Domicile -> {
//            settingsHelper.save(KeySettingsType.CITY, type.city)
//            settingsHelper.save(KeySettingsType.COUNTRY, type.country)
//            settingsHelper.save(KeySettingsType.PROVINCE, type.province)
//        }
//
//        is UpdateProfileType.Fullname -> {
//            settingsHelper.save(KeySettingsType.LAST_NAME, type.lastName)
//            settingsHelper.save(KeySettingsType.FIRST_NAME, type.firstName)
//        }
//
//        is UpdateProfileType.Gender -> {
//            settingsHelper.save(KeySettingsType.ID_GENDER, type.gender)
//        }
//
//        is UpdateProfileType.PhoneNumber -> {
//            settingsHelper.save(KeySettingsType.PHONE_NUMBER, type.phoneNumber)
//        }
//    }
//}