package com.kompasid.netdatalibrary.core.data.updateProfile.repository

import com.kompasid.netdatalibrary.base.network.ApiResults
import com.kompasid.netdatalibrary.base.network.NetworkError
import com.kompasid.netdatalibrary.base.network.Results
import com.kompasid.netdatalibrary.core.data.updateProfile.dto.request.UpdateProfileRequest
import com.kompasid.netdatalibrary.core.data.updateProfile.network.UpdateProfileApiService
import com.kompasid.netdatalibrary.core.domain.personalInfo.other.UpdateProfileType

import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper


class UpdateProfileRepository(
    private val updateProfileApiService: UpdateProfileApiService,
    private val settingsHelper: SettingsHelper
) : IUpdateProfileRepository {

    suspend fun updateProfile(type: UpdateProfileType): Results<Unit, NetworkError> =
        runCatching {
            val baseRequest = UpdateProfileRequest(
                city = settingsHelper.get(KeySettingsType.CITY, ""),
                country = settingsHelper.get(KeySettingsType.COUNTRY, ""),
                dateBirth = settingsHelper.get(KeySettingsType.DATE_BIRTH, ""),
                firstName = settingsHelper.get(KeySettingsType.FIRST_NAME, ""),
                gender = settingsHelper.get(KeySettingsType.ID_GENDER, 0),
                lastName = settingsHelper.get(KeySettingsType.LAST_NAME, ""),
                phoneNumber = settingsHelper.get(KeySettingsType.PHONE_NUMBER, ""),
                province = settingsHelper.get(KeySettingsType.PROVINCE, ""),
                userName = settingsHelper.get(KeySettingsType.USERNAME, "")
            )

            val request = when (type) {
                is UpdateProfileType.DateBirth -> baseRequest.copy(dateBirth = type.dateBirth)
                is UpdateProfileType.Domicile -> baseRequest.copy(
                    city = type.city,
                    country = type.country,
                    province = type.province
                )

                is UpdateProfileType.Fullname -> baseRequest.copy(
                    firstName = type.firstName,
                    lastName = type.lastName
                )

                is UpdateProfileType.Gender -> baseRequest.copy(gender = type.gender)
                is UpdateProfileType.PhoneNumber -> baseRequest.copy(phoneNumber = type.phoneNumber)
            }

            when (val result = updateProfileApiService.updateProfile(request)) {
                is ApiResults.Success -> {
                    Results.Success(Unit)
                }

                is ApiResults.Error -> Results.Error(result.error)
            }
        }.getOrElse { exception ->
            Results.Error(NetworkError.Error(exception))
        }

}
