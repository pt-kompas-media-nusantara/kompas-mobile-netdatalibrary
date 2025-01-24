package com.kompasid.netdatalibrary.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kompasid.netdatalibrary.android.AppBackBar
import com.kompasid.netdatalibrary.core.presentation.general_content_presentation.GeneralContentVM
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AppIconUseCaseScreen(
    vm: GeneralContentVM = koinViewModel(),
    onBackClick: () -> Unit,
) {

    val state by vm.data.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppBackBar(onBackClick)

        Text("AppIconUseCaseScreen")
        HorizontalDivider()

        FilledButton("App Icon", {
            vm.getGeneralData()
        })

        Text("Data General Content ${state.toString()}")
    }
}
