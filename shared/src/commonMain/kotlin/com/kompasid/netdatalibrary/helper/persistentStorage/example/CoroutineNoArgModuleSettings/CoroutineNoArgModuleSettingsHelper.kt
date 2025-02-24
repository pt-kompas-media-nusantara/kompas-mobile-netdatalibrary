package com.kompasid.netdatalibrary.helper.persistentStorage.example.CoroutineNoArgModuleSettings

import com.russhwolf.settings.coroutines.SuspendSettings

class CoroutineNoArgModuleSettingsHelper(private val settings: SuspendSettings) {

    // Fungsi untuk mengambil nilai secara suspend
    suspend fun getUsername(): String {
        return settings.getString("username", "Guest")
    }

    suspend fun getAge(): Int {
        return settings.getInt("age", 25)
    }

    suspend fun isDarkModeEnabled(): Boolean {
        return settings.getBoolean("dark_mode", false)
    }

    suspend fun getVolume(): Float {
        return settings.getFloat("volume", 0.5f)
    }

    // Fungsi untuk menyimpan nilai secara suspend
    suspend fun updateUsername(value: String) {
        settings.putString("username", value)
    }

    suspend fun updateAge(value: Int) {
        settings.putInt("age", value)
    }

    suspend fun updateDarkMode(value: Boolean) {
        settings.putBoolean("dark_mode", value)
    }

    suspend fun updateVolume(value: Float) {
        settings.putFloat("volume", value)
    }
}
