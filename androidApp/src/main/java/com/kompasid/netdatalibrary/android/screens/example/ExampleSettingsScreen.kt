package com.kompasid.netdatalibrary.android.screens.example


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.kompasid.netdatalibrary.helper.persistentStorage.example.ExampleSettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExampleSettingsScreen(viewModel: ExampleSettingsViewModel = koinViewModel()) {
    // Observasi data dari ViewModel secara real-time
    val username by viewModel.username.collectAsState()
    val darkMode by viewModel.darkMode.collectAsState()

    var textState by remember { mutableStateOf(TextFieldValue(username)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("User Settings", style = MaterialTheme.typography.headlineMedium)

        // Input untuk Username
        BasicTextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.fillMaxWidth()
        )

        // Tombol untuk Menyimpan Username
        Button(onClick = {
            viewModel.updateUsername(textState.text)
        }) {
            Text("Save Username")
        }

        // Toggle untuk Dark Mode
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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