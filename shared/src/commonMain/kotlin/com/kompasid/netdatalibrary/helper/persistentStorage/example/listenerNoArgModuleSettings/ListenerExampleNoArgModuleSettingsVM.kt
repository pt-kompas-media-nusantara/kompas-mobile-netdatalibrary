package com.kompasid.netdatalibrary.helper.persistentStorage.example.listenerNoArgModuleSettings

import com.kompasid.netdatalibrary.BaseVM
import kotlinx.coroutines.flow.StateFlow

class ListenerExampleNoArgModuleSettingsVM(private val settingsDataSource: ListenerNoArgModuleSettingsHelper) : BaseVM() {

    val username: StateFlow<String> = settingsDataSource.usernameFlow
    val darkMode: StateFlow<Boolean> = settingsDataSource.darkModeFlow

    fun updateUsername(newUsername: String) {
        settingsDataSource.saveUsername(newUsername)
    }

    fun toggleDarkMode() {
        val newValue = !darkMode.value
        settingsDataSource.saveDarkMode(newValue)
    }

    fun close() {
        settingsDataSource.removeListeners() // Hapus listener saat ViewModel dihancurkan
    }
}
