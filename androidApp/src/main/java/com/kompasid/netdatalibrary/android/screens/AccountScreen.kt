package com.kompasid.netdatalibrary.android.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
//    val aboutApp by vm.aboutApp.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // 🔹 Back Button
        AppBackBar(onBackClick)

        // 🔹 Judul
        Text(
            text = "Account Screen",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Versi Aplikasi
        Text(
            text = "Versi Aplikasi",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
//        Text(
//            text = aboutApp.appVersion,
//            style = MaterialTheme.typography.bodyLarge
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//        HorizontalDivider()
//
//        // 🔹 Deskripsi
//        Text(
//            text = aboutApp.desc,
//            style = MaterialTheme.typography.bodyMedium,
//            textAlign = TextAlign.Justify,
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//        HorizontalDivider()
//
//        // 🔹 Informasi Perangkat
//        Text(
//            text = aboutApp.appInfoTitle,
//            style = MaterialTheme.typography.bodyMedium,
//            fontWeight = FontWeight.SemiBold,
//            modifier = Modifier.padding(vertical = 8.dp)
//        )
//
//        // 🔹 Tampilkan List Informasi Perangkat
//        aboutApp.appInfo.forEach { info ->
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(text = info.key, style = MaterialTheme.typography.bodyMedium)
//                Text(
//                    text = info.value,
//                    style = MaterialTheme.typography.bodyMedium,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
    }
}

