package com.kompasid.netdatalibrary.core.domain.settings

import com.kompasid.netdatalibrary.base.logger.Logger
import com.kompasid.netdatalibrary.core.domain.settings.enums.BooleanKeySettingsType
import com.kompasid.netdatalibrary.core.domain.settings.enums.FloatKeySettingsType
import com.kompasid.netdatalibrary.core.domain.settings.enums.IntKeySettingsType
import com.kompasid.netdatalibrary.core.domain.settings.enums.StringKeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class SettingsUseCase(
    private val settingsHelper: SettingsHelper,
) {
    suspend fun load(type: StringKeySettingsType): String = withContext(Dispatchers.IO) {
        try {
            val keySettingsType = KeySettingsType.valueOf(type.name) // Konversi otomatis enum
            settingsHelper.get(keySettingsType, "")
        } catch (e: IllegalArgumentException) {
            Logger.error { e.toString() }
            ""
        }
    }

    suspend fun load(type: IntKeySettingsType): Int = withContext(Dispatchers.IO) {
        try {
            val keySettingsType = KeySettingsType.valueOf(type.name) // Konversi otomatis enum
            settingsHelper.get(keySettingsType, 99)
        } catch (e: IllegalArgumentException) {
            Logger.error { e.toString() }
            99
        }
    }

    suspend fun load(type: FloatKeySettingsType): Float = withContext(Dispatchers.IO) {
        try {
            val keySettingsType = KeySettingsType.valueOf(type.name) // Konversi otomatis enum
            settingsHelper.get(keySettingsType, 0f)
        } catch (e: IllegalArgumentException) {
            Logger.error { e.toString() }
            0f
        }
    }

    suspend fun load(type: BooleanKeySettingsType): Boolean = withContext(Dispatchers.IO) {
        try {
            val keySettingsType = KeySettingsType.valueOf(type.name) // Konversi otomatis enum
            settingsHelper.get(keySettingsType, false)
        } catch (e: IllegalArgumentException) {
            Logger.error { e.toString() }
            false
        }
    }
}


//KeySettingsType.LOGIN_TYPE -> settingsHelper.get(KeySettingsType.LOGIN_TYPE, "")
//KeySettingsType.EXPIRED_MEMBERSHIPS -> settingsHelper.get(KeySettingsType.EXPIRED_MEMBERSHIPS, "")
//KeySettingsType.ACTIVE_MEMBERSHIPS -> settingsHelper.get(KeySettingsType.ACTIVE_MEMBERSHIPS, "")
//KeySettingsType.HISTORY_TRANSACTION -> settingsHelper.get(KeySettingsType.HISTORY_TRANSACTION, "")
