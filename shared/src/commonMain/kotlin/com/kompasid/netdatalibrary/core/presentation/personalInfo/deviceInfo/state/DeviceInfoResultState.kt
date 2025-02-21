package com.kompasid.netdatalibrary.core.presentation.personalInfo.deviceInfo.state

import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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

    suspend fun getString(type: KeySettingsType) {
        settingsUseCase.getString(type)
    }

    suspend fun getBoolean(type: KeySettingsType) {
        settingsUseCase.getBoolean(type)
    }

    suspend fun getInt(type: KeySettingsType) {
        settingsUseCase.getInt(type)
    }
}
// cari cara bagaimana caranya, ketika ada perubahan dari settingsUseCase bisa langsung disimpan ke
// dalam variable tersebut dan perubahannya realtime