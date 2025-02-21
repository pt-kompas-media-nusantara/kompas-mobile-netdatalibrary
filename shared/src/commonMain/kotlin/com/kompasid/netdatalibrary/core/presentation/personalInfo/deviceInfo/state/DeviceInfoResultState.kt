package com.kompasid.netdatalibrary.core.presentation.personalInfo.deviceInfo.state

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.domain.settings.usecase.SettingsUseCase
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DeviceInfoState(
    val originalIdTransaksi: String = "--"
)

class DeviceInfoResultState(
    private val settingsUseCase: SettingsUseCase
) {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            settingsUseCase.observeString(KeySettingsType.ORIGINAL_TRANSACTION_ID)
                .filterNotNull()
                .distinctUntilChanged() // Mencegah update jika value tidak berubah
                .collect { newValue ->
                    Logger.debug { "Updating originalIdTransaksi: $newValue" }
                    _state.update { it.copy(originalIdTransaksi = newValue) }
                }
        }
    }

    private val _state = MutableStateFlow(DeviceInfoState())
    val state: StateFlow<DeviceInfoState> = _state.asStateFlow()

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
