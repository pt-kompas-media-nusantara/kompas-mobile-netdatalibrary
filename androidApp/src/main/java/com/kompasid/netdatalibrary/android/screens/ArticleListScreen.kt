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
import com.kompasid.netdatalibrary.netData.presentation.articlesPresentation.ArticlesVM
import com.kompasid.netdatalibrary.android.AppBackBar
import org.koin.androidx.compose.koinViewModel


@Composable
fun ArticleListScreen(
    articlesVM: ArticlesVM = koinViewModel<ArticlesVM>(),
    onBackClick: () -> Unit,
) {

//    LaunchedEffect(Unit) {
//        articlesVM.getAllArticleList()
//    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppBackBar(onBackClick)
        Text("Home Screen")
        HorizontalDivider()

//        FilledButton("Home", {
//            articlesVM.getAllArticleList()
//        })
//
//        FilledButton("Article Detail", {
//            articlesVM.articleDetail()
//        })
    }
}