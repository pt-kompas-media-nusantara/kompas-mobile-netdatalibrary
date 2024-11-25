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
import com.kompasid.netdatalibrary.netData.presentation.articlesPresentation.ArticlesVM
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    articlesVM: ArticlesVM = getViewModel(),
    onAccountClick: () -> Unit,
    onArticleListClick: () -> Unit,
    onSettinsClick: () -> Unit,
    onAuthUseCaseClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        FilledButton("Settings View", {
            onSettinsClick()
        })

        FilledButton("Auth Use Case", {
            onAuthUseCaseClick()
        })

//        FilledButton("Login Guest - Api Service", {
//            articlesVM.postLoginGuest()
//        })
//
//        FilledButton("Login By Email", {
//            articlesVM.postLoginByEmail()
//        })
//
//        FilledButton("Refresh Token", {
//            articlesVM.updateToken()
//        })
//
//        FilledButton("Logout", {
//            articlesVM.postLogout()
//        })
//
//        FilledButton("Article List Screen", {
//            onArticleListClick()
//        })
//
//        FilledButton("Account - Baca Nanti", {
//            onAccountClick()
//        })
    }


}

@Composable
fun FilledButton(text: String, onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text(text)
    }
}

// https://medium.com/@italord.melo/voyager-compose-multiplatform-navigation-and-viewmodels-screenmodel-b36693484d98