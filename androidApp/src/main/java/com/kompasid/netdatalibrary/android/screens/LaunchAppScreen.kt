package com.kompasid.netdatalibrary.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.kompasid.netdatalibrary.core.presentation.launchApp.stateState.LaunchAppVM
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LaunchAppScreen(
    vm: LaunchAppVM = koinViewModel(),
    onBackClick: () -> Unit,
) {
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

            FilledButton("Execute") {
                vm.saveList()
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


//@Composable
//fun LaunchAppScreen(
//    vm: LaunchAppVM = koinViewModel(),
//    onBackClick: () -> Unit,
//) {
////    val originalTransactionId by vm.originalTransactionId.collectAsState()
////    val deviceInfoState by vm.deviceInfoState.collectAsState()
////    val deviceSubcriptionState by vm.deviceSubscriptionState.collectAsState() // Perbaikan
////    val configurationSystemState by vm.configurationSystemState.collectAsState() // Perbaikan
//
//    val originalTransactionId by vm.originalTransactionId.collectAsState()
//    val transactionId by vm.transactionId.collectAsState()
//    val historyTransaction by vm.historyTransaction.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize() // Menyebarkan layout ke seluruh layar
//            .padding(16.dp)
//            .verticalScroll(rememberScrollState()) // Scroll agar bisa menggulir konten yang panjang
//    ) {
//        AppBackBar(onBackClick)
//
//        Text("LaunchAppScreen")
//        HorizontalDivider()
//
//        Text("Original Transaction ID:")
//        LazyColumn {
//            items(originalTransactionId) { item ->
//                Text("- $item", fontSize = 16.sp)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text("Transaction ID:")
//        LazyColumn {
//            items(transactionId) { item ->
//                Text("- $item", fontSize = 16.sp)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text("History Transaction:")
//        LazyColumn {
//            items(historyTransaction) { item ->
//                Text("- $item", fontSize = 16.sp)
//            }
//        }
//
////        // Menampilkan data dengan lebih rapi menggunakan Modifier.padding()
////        Text(deviceInfoState.toString(), modifier = Modifier.padding(top = 8.dp))
////        HorizontalDivider()
////        Text(deviceSubcriptionState.toString(), modifier = Modifier.padding(top = 8.dp))
////        HorizontalDivider()
////        Text(configurationSystemState.toString(), modifier = Modifier.padding(top = 8.dp))
////        HorizontalDivider()
////        Spacer(modifier = Modifier.height(16.dp))
////
////        FilledButton("Execute") {
////            vm.scope.launch {
////                vm.execute()
////            }
////        }
//
//        FilledButton("Execute") {
//            vm.saveList()
//        }
//
//        FilledButton("Execute") {
//            vm.getList()
//        }
//
//    }
//}

