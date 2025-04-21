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
//    val originalTransactionId by vm.deviceInfoState.collectAsState()
//    val transactionId by vm.deviceSubscriptionState.collectAsState()
//    val historyTransaction by vm.configurationSystemState.collectAsState()

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

            FilledButton("executeTest") {
                vm.executeTest()
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

//        item {
//            Text(originalTransactionId.toString())
//            HorizontalDivider()
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(transactionId.toString())
//            HorizontalDivider()
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(historyTransaction.toString())
//            HorizontalDivider()
//            Spacer(modifier = Modifier.height(16.dp))
//        }
    }
}

