package com.kompasid.netdatalibrary.helper.persistentStorage.example.coroutineNoArgModuleSettings


import com.kompasid.netdatalibrary.BaseVM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoroutineNoArgModuleSettingsVM(private val settingsDataSource: CoroutineNoArgModuleSettingsHelper) : BaseVM() {

    private val _username = MutableStateFlow("Guest")
    val username = _username.asStateFlow()

    private val _age = MutableStateFlow(25)
    val age = _age.asStateFlow()

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode = _isDarkMode.asStateFlow()

    private val _volume = MutableStateFlow(0.5f)
    val volume = _volume.asStateFlow()

    init {
        scope.launch {
            _username.value = settingsDataSource.getUsername()
            _age.value = settingsDataSource.getAge()
            _isDarkMode.value = settingsDataSource.isDarkModeEnabled()
            _volume.value = settingsDataSource.getVolume()
        }
    }

    fun updateUsername(newUsername: String) {
        scope.launch {
            settingsDataSource.updateUsername(newUsername)
            _username.value = newUsername
        }
    }

    fun updateAge(newAge: Int) {
        scope.launch {
            settingsDataSource.updateAge(newAge)
            _age.value = newAge
        }
    }

    fun updateDarkMode(enabled: Boolean) {
        scope.launch {
            settingsDataSource.updateDarkMode(enabled)
            _isDarkMode.value = enabled
        }
    }

    fun updateVolume(level: Float) {
        scope.launch {
            settingsDataSource.updateVolume(level)
            _volume.value = level
        }
    }
}
