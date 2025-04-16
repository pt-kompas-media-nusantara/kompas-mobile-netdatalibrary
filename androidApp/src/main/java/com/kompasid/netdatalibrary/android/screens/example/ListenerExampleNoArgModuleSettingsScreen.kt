package com.kompasid.netdatalibrary.android.screens.example

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.kompasid.netdatalibrary.android.AppBackBar
import com.kompasid.netdatalibrary.helper.persistentStorage.example.listenerNoArgModuleSettings.ListenerExampleNoArgModuleSettingsVM
import com.kompasid.netdatalibrary.helpers.times.RelativeTimeFormatter
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListenerExampleNoArgModuleSettingsScreen(
    viewModel: ListenerExampleNoArgModuleSettingsVM = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val username by viewModel.username.collectAsState()
    val darkMode by viewModel.darkMode.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.close() // Hapus listener saat composable ini tidak digunakan lagi
        }
    }

    Column {
        AppBackBar(onBackClick)

        Text("Username: $username")
        Button(onClick = { viewModel.updateUsername("Test ${RelativeTimeFormatter().getCurrentTime()}") }) {
            Text("Update Username")
        }

        Row {
            Text("Dark Mode")
            Switch(
                checked = darkMode,
                onCheckedChange = { viewModel.toggleDarkMode() }
            )
        }

        // Menampilkan Nilai yang Sudah Disimpan
        Text(text = "Current Username: $username")
        Text(text = "Dark Mode: ${if (darkMode) "Enabled" else "Disabled"}")
    }
}
