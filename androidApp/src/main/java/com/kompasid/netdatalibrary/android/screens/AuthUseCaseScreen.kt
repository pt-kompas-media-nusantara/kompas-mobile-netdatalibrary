package com.kompasid.netdatalibrary.android.screens


import androidx.compose.foundation.layout.Column
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
import com.kompasid.netdatalibrary.core.presentation.authPresentation.AuthVM
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AuthUseCaseScreen(
    vm: AuthVM = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val accessToken by vm.accessToken.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppBackBar(onBackClick)

        Text("AuthUseCaseScreen")
        HorizontalDivider()

        FilledButton("Login Anon", {
            vm.postLoginGuest()
        })

        FilledButton("Login by Email", {
            vm.postLoginByEmail()
        })

        FilledButton("Logout", {
            vm.postLogout()
        })

        FilledButton("Decode JWT", {
            vm.decodeJWT()
        })

        FilledButton("Cetak All Settings", {
            vm.cetakAllSettings()
        })

        FilledButton("Cetak Access Token", {
            vm.cetakAccessSettings()
        })

        Text("accessToken: $accessToken")
    }
}
