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
// import com.kompasid.netdatalibrary.netData.presentation.articlesPresentation.ArticlesVM
import com.kompasid.netdatalibrary.android.AppBackBar
import com.kompasid.netdatalibrary.core.presentation.AccountVM
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AccountScreen(
    vm: AccountVM = koinViewModel(),
    onBackClick: () -> Unit,
) {


    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppBackBar(onBackClick)

        Text("AccountScreen")
        HorizontalDivider()

//        FilledButton("Test Interceptor Access & Refresh Token", {
//            articlesVM.interceptorToken()
//        })
//
//        FilledButton("Bookmark List - First", {
//            articlesVM.getArticleBookmark()
//        })
//
//        FilledButton("Bookmark List - Load More", {
//            articlesVM.getLoadMoreArticleBookmark()
//        })
//
//        FilledButton("Save Bookmark", {
//            articlesVM.saveArticleBookmark()
//        })
//
//        FilledButton("Remove Bookmark", {
//            articlesVM.removeArticleBookmark("mengapa-orang-amerika-lebih-percaya-pada-trump")
//        })
//
//        FilledButton("Remove All", {
//            articlesVM.removeAllArticleBookmark()
//        })
//
//        FilledButton("Rubrik Pilihanku List", {
//            articlesVM.rubrikPilihankuList()
//        })
//
//        FilledButton("Rubrik Pilihanku - Save", {
//            articlesVM.callSaveRubrikPilihanku()
//        })
//
//        FilledButton("QnA", {
//            articlesVM.qna()
//        })
//
//        FilledButton("App Icon", {
//            articlesVM.appIcon()
//        })
//
//        FilledButton("Widget Iframe Berita Utama", {
//            articlesVM.widgetIframeBeritaUtama()
//        })
//
//        FilledButton("Widget Iframe Article Detail", {
//            articlesVM.widgetIframeArticleDetail()
//        })
//
//        FilledButton("Subscription Landing Page", {
//            articlesVM.subscriptionLandingPage()
//        })
//
//        FilledButton("Subscription Products", {
//            articlesVM.subscriptionProducts()
//        })
//
//        FilledButton("Repola Iframe", {
//            articlesVM.repolaIframe()
//        })
//
//        FilledButton("Device List", {
//            articlesVM.deviceList()
//        })
//
//        FilledButton("Revoke Device All", {
//            articlesVM.revokeDeviceAll()
//        })
//
//        FilledButton("Revoke Device Spesific", {
//            articlesVM.revokeDeviceSpesific()
//        })
//
//        FilledButton("Landing Page Tagar", {
//            articlesVM.landingPageTagar()
//        })

    }
}

