package com.kompasid.netdatalibrary.core.domain.updateOS.useCase

import com.kompasid.netdatalibrary.core.domain.updateOS.model.UpdateStatusOSType
import com.kompasid.netdatalibrary.helper.persistentStorage.KeySettingsType
import com.kompasid.netdatalibrary.helper.persistentStorage.SettingsHelper
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class UpdateOSUseCase(
    private val settingsHelper: SettingsHelper
) {

    suspend fun checkOSUpdate(): UpdateStatusOSType {
        // Ambil nilai pertama dari StateFlow (hanya sekali)
        val currentOSVersion = settingsHelper.getStringFlow(KeySettingsType.OS_VERSION).firstOrNull()?.takeIf { it.isNotEmpty() }
            ?: return UpdateStatusOSType.NO_UPDATE_OS

        val recommendationOSVersions = settingsHelper.getStringFlow(KeySettingsType.RECOMENDATION_OS_VERSION).firstOrNull()?.takeIf { it.isNotEmpty() }
            ?: return UpdateStatusOSType.NO_UPDATE_OS

        // Konversi ke Double untuk perbandingan
        val currentVersion = currentOSVersion.toDoubleOrNull() ?: return UpdateStatusOSType.NO_UPDATE_OS
        val recommendedVersion = recommendationOSVersions.toDoubleOrNull() ?: return UpdateStatusOSType.NO_UPDATE_OS

        return if (currentVersion < recommendedVersion) {
            UpdateStatusOSType.UPDATE_OS
        } else {
            UpdateStatusOSType.NO_UPDATE_OS
        }
    }



}