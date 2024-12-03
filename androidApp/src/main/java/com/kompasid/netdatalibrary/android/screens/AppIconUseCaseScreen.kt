package com.kompasid.netdatalibrary.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kompasid.netdatalibrary.android.AppBackBar
import com.kompasid.netdatalibrary.netData.presentation.appIconPresentation.AppIconVM
import org.koin.androidx.compose.getViewModel


@Composable
fun AppIconUseCaseScreen(
    vm: AppIconVM = getViewModel(),
    onBackClick: () -> Unit,
) {


    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppBackBar(onBackClick)

        Text("AppIconUseCaseScreen")
        HorizontalDivider()

        FilledButton("App Icon", {
            vm.appIcon()
        })

    }
}
