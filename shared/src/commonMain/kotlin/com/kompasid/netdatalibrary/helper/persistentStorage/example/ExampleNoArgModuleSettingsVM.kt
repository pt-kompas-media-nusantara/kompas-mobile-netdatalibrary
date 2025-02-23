package com.kompasid.netdatalibrary.helper.persistentStorage.example

import com.kompasid.netdatalibrary.BaseVM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExampleNoArgModuleSettingsVM(private val settingHelper: ExampleNoArgModuleSettingsHelper) : BaseVM() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _darkMode = MutableStateFlow(false)
    val darkMode: StateFlow<Boolean> = _darkMode.asStateFlow()

    init {
        // Load nilai awal saat ViewModel dibuat
        _username.value = settingHelper.load(ExampleSettingsKey.USERNAME, "Guest")
        _darkMode.value = settingHelper.load(ExampleSettingsKey.DARK_MODE, false)
    }

    fun updateUsername(newUsername: String) {
        scope.launch {
            settingHelper.save(ExampleSettingsKey.USERNAME, newUsername)
            _username.value = newUsername  // Update state agar UI ikut berubah
        }
    }

    fun toggleDarkMode() {
        scope.launch {
            val newValue = !_darkMode.value
            settingHelper.save(ExampleSettingsKey.DARK_MODE, newValue)
            _darkMode.value = newValue  // Update state agar UI ikut berubah
        }
    }
}