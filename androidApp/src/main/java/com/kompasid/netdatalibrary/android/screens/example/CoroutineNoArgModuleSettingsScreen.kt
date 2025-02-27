package com.kompasid.netdatalibrary.android.screens.example

import com.kompasid.netdatalibrary.helper.persistentStorage.example.coroutineNoArgModuleSettings.CoroutineNoArgModuleSettingsVM
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kompasid.netdatalibrary.android.AppBackBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CoroutineNoArgModuleSettingsScreen(
    viewModel: CoroutineNoArgModuleSettingsVM = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val username by viewModel.username.collectAsState()
    val age by viewModel.age.collectAsState()
    val isDarkMode by viewModel.isDarkMode.collectAsState()
    val volume by viewModel.volume.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AppBackBar(onBackClick)

        Text("User Settings", style = MaterialTheme.typography.headlineMedium)

        // Menampilkan Username
        Text("Username: $username")
        Button(onClick = { viewModel.updateUsername("NewUser") }) {
            Text("Update Username")
        }

        // Menampilkan Age
        Text("Age: $age")
        Button(onClick = { viewModel.updateAge(age + 1) }) {
            Text("Increase Age")
        }

        // Menampilkan Dark Mode
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Dark Mode")
            Switch(
                checked = isDarkMode,
                onCheckedChange = { viewModel.updateDarkMode(it) }
            )
        }

        // Menampilkan Volume
        Text("Volume: $volume")
        Slider(
            value = volume,
            onValueChange = { viewModel.updateVolume(it) },
            valueRange = 0f..1f
        )
    }
}
