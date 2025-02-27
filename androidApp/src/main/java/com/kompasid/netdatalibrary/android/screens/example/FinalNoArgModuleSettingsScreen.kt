package com.kompasid.netdatalibrary.android.screens.example

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kompasid.netdatalibrary.android.AppBackBar
import com.kompasid.netdatalibrary.android.screens.FilledButton
import com.kompasid.netdatalibrary.core.presentation.launchApp.stateState.LaunchAppVM
import com.kompasid.netdatalibrary.helper.persistentStorage.example.finalNoArgModuleSettings.FinalNoArgModuleSettingsVM
import org.koin.compose.viewmodel.koinViewModel

class FinalNoArgModuleSettingsScreen {
}

@Composable
fun FinalNoArgModuleSettingsScreen(
    vm: FinalNoArgModuleSettingsVM = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val trytry by vm.trytry.collectAsState()
    val originalTransactionId by vm.originalTransactionId.collectAsState()
    val transactionId by vm.transactionId.collectAsState()
    val historyTransaction by vm.historyTransaction.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Gunakan padding agar tidak terlalu mepet ke tepi layar
    ) {
        item {
            AppBackBar(onBackClick)
            Text("LaunchAppScreen", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            FilledButton("saveList") {
                vm.saveList()
            }

            FilledButton("saveModel") {
                vm.saveModel()
            }
        }

        item {
            Text(text = "Model Original Transaction IDs:", fontWeight = FontWeight.Bold)
            trytry.originalTransactionId.forEach { id ->
                Text(text = id)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Model Transaction IDs:", fontWeight = FontWeight.Bold)
            trytry.transactionId.forEach { id ->
                Text(text = id)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Model History Transactions:", fontWeight = FontWeight.Bold)
            trytry.historyTransaction.forEach { id ->
                Text(text = id)
            }
        }

        item {
            Text("Original Transaction ID:")
        }
        items(originalTransactionId) { item ->
            Text("- $item", fontSize = 16.sp)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Transaction ID:")
        }
        items(transactionId) { item ->
            Text("- $item", fontSize = 16.sp)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text("History Transaction:")
        }
        items(historyTransaction) { item ->
            Text("- $item", fontSize = 16.sp)
        }
    }
}