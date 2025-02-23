package com.kompasid.netdatalibrary.helper.persistentStorage.example.ListenerNoArgModuleSettings

import com.kompasid.netdatalibrary.helper.persistentStorage.example.NoArgModuleSettings.ExampleSettingsKey
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SettingsListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListenerNoArgModuleSettingsHelper(
    private val settings: ObservableSettings
) {

    private val _usernameFlow =
        MutableStateFlow(settings.getString(ExampleSettingsKey.USERNAME.value, "Guest"))
    val usernameFlow = _usernameFlow.asStateFlow()

    private val _darkModeFlow =
        MutableStateFlow(settings.getBoolean(ExampleSettingsKey.DARK_MODE.value, false))
    val darkModeFlow = _darkModeFlow.asStateFlow()

    private var usernameListener: SettingsListener? = null
    private var darkModeListener: SettingsListener? = null

    init {
        // Listener untuk Username
        usernameListener = settings.addStringOrNullListener(
            ExampleSettingsKey.USERNAME.value
        ) { newValue ->
            _usernameFlow.value = newValue ?: "-" // Jika null, gunakan default "Guest"
        }

        // Listener untuk Dark Mode
        darkModeListener = settings.addBooleanOrNullListener(
            ExampleSettingsKey.DARK_MODE.value
        ) { newValue ->
            _darkModeFlow.value = newValue ?: false // Jika null, gunakan default false
        }
    }

    fun saveUsername(newUsername: String) {
        settings.putString(ExampleSettingsKey.USERNAME.value, newUsername)
    }

    fun saveDarkMode(isEnabled: Boolean) {
        settings.putBoolean(ExampleSettingsKey.DARK_MODE.value, isEnabled)
    }

    fun removeListeners() {
        usernameListener?.deactivate()
        darkModeListener?.deactivate()
    }
}
