package com.kompasid.netdatalibrary.android.screens.example

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kompasid.netdatalibrary.android.AppBackBar
import com.kompasid.netdatalibrary.helper.persistentStorage.example.SerializationNoArgModuleSettings.SerializationNoArgModuleSettingsVM
import com.kompasid.netdatalibrary.helper.persistentStorage.example.SerializationNoArgModuleSettings.model.Address
import com.kompasid.netdatalibrary.helper.persistentStorage.example.SerializationNoArgModuleSettings.model.UserProfile
import com.kompasid.netdatalibrary.helpers.date.RelativeTimeFormatter
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SerializationNoArgModuleSettingsScreen(
    viewModel: SerializationNoArgModuleSettingsVM = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val userProfile by viewModel.userProfile.collectAsState()
    val addressList by viewModel.addressList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AppBackBar(onBackClick)

        Text("User Profile", style = MaterialTheme.typography.headlineMedium)

        Text("Name: ${userProfile.name}")
        Text("Age: ${userProfile.age}")
        Text("Email: ${userProfile.email}")

        Button(onClick = {
            viewModel.updateUserProfile(UserProfile("Test - ${RelativeTimeFormatter().getCurrentTime()}", 25, "john@example.com"))
        }) {
            Text("Update Profile")
        }

        Button(onClick = { viewModel.removeUserProfile() }) {
            Text("Remove Profile")
        }

        Text("Saved Addresses", style = MaterialTheme.typography.headlineMedium)

        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        addressList.forEach { address ->
            Text("${address.street}, ${address.city} - ${address.postalCode}")
        }

        Button(onClick = {
            val newAddresses = listOf(
                Address("Test - ${RelativeTimeFormatter().getCurrentTime()}", "Jakarta", "10110"),
                Address("Test - ${RelativeTimeFormatter().getCurrentTime()}", "Bandung", "40241")
            )
            viewModel.updateAddressList(newAddresses)
        }) {
            Text("Save New Addresses")
        }

        Button(onClick = { viewModel.removeAddressList() }) {
            Text("Clear Addresses")
        }
    }
}
