package com.kompasid.netdatalibrary.android.screens.errors

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment


@Composable
fun ErrorView() {
    Box(contentAlignment = Alignment.Center) {
        Text("Error occurred!", color = MaterialTheme.colorScheme.error)
    }
}