package com.kompasid.netdatalibrary.android.screens.kotor.errors

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment


@Composable
fun MaintenanceView() {
    Box(contentAlignment = Alignment.Center) {
        Text("Error occurred!", color = MaterialTheme.colorScheme.error)
    }
}