package com.kompasid.netdatalibrary.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MainScreen(
    onNoArgModuleSettingsClick: () -> Unit,
    onListeneroArgModuleSettingsClick: () -> Unit,
    onSerializationNoArgModuleSettingsClick: () -> Unit,
    onLaunchAppFlowClick: () -> Unit,
    onAuthFlowClick: () -> Unit,
    onAccountClick: () -> Unit,
    onManageAccountClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onRewardClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onContactUsClick: () -> Unit,
    onQnAClick: () -> Unit,
    onAboutAppClick: () -> Unit,
    onAboutHarianKompasClick: () -> Unit,
    ) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        FilledButton("onNoArgModuleSettingsClick", {
            onNoArgModuleSettingsClick()
        })

        FilledButton("onListeneroArgModuleSettingsClick", {
            onListeneroArgModuleSettingsClick()
        })

        FilledButton("onSerializationNoArgModuleSettingsClick", {
            onSerializationNoArgModuleSettingsClick()
        })

        FilledButton("onLaunchAppFlowClick", {
            onLaunchAppFlowClick()
        })

        FilledButton("onAuthFlowClick", {
            onAuthFlowClick()
        })

        FilledButton("onAccountClick", {
            onAccountClick()
        })

        FilledButton("onManageAccountClick", {
            onManageAccountClick()
        })

        FilledButton("onBookmarksClick", {
            onBookmarksClick()
        })

        FilledButton("onRewardClick", {
            onRewardClick()
        })

        FilledButton("onSettingsClick", {
            onSettingsClick()
        })

        FilledButton("onContactUsClick", {
            onContactUsClick()
        })

        FilledButton("onQnAClick", {
            onQnAClick()
        })

        FilledButton("onAboutAppClick", {
            onAboutAppClick()
        })

        FilledButton("onAboutHarianKompasClick", {
            onAboutHarianKompasClick()
        })

    }


}

@Composable
fun FilledButton(text: String, onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text(text)
    }
}

// https://medium.com/@italord.melo/voyager-compose-multiplatform-navigation-and-viewmodels-screenmodel-b36693484d98