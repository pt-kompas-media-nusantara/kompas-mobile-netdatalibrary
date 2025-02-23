package com.kompasid.netdatalibrary.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kompasid.netdatalibrary.android.AppBackBar
import com.kompasid.netdatalibrary.core.presentation.auth.resultState.AuthVM
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AuthScreen(
    vm: AuthVM = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val loginInterceptor by vm.loginInterceptor.collectAsState()
    val userDetailResInterceptor by vm.userDetailResInterceptor.collectAsState()
    val userHistoryMembershipResInterceptor by vm.userHistoryMembershipResInterceptor.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize() // Menyebarkan layout ke seluruh layar
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Scroll agar bisa menggulir konten yang panjang
    ) {
        AppBackBar(onBackClick)

        Text("AuthScreen")
        HorizontalDivider()
        FilledButton("Execute") {
            vm.scope.launch {
                vm.loginByEmail()
            }
        }
        FilledButton("removaAll") {
            vm.scope.launch {
                vm.removaAll()
            }
        }
        FilledButton("logger") {
            vm.scope.launch {
                vm.logger()
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Text(loginInterceptor.toString(), modifier = Modifier.padding(top = 8.dp))
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Text(userDetailResInterceptor.toString(), modifier = Modifier.padding(top = 8.dp))
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Text(userHistoryMembershipResInterceptor.toString(), modifier = Modifier.padding(top = 8.dp))
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))


    }
}

