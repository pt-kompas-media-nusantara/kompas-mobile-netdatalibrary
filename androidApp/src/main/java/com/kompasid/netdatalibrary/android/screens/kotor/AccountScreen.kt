package com.kompasid.netdatalibrary.android.screens.kotor


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
// import com.kompasid.netdatalibrary.netData.presentation.articlesPresentation.ArticlesVM
import com.kompasid.netdatalibrary.android.AppBackBar
import com.kompasid.netdatalibrary.core.presentation.AccountVM
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AccountScreen(
    vm: AccountVM = koinViewModel(),
    onBackClick: () -> Unit,
) {

    val userDetail by vm.userDetail.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppBackBar(onBackClick)

        Text("AccountScreen")
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()

        Text("idGender: ${userDetail.idGender}")
        Text("gender: ${userDetail.gender}")
        Text("userId: ${userDetail.userId}")
        Text("firstName: ${userDetail.firstName}")
        Text("lastName: ${userDetail.lastName}")
        Text("email: ${userDetail.email}")
        Text("userGuid: ${userDetail.userGuid}")
        Text("isActive: ${userDetail.isActive}")
        Text("isVerified: ${userDetail.userStatus.isVerified}")
        Text("phoneVerified: ${userDetail.userStatus.phoneVerified}")
        Text("phoneNumber: ${userDetail.phoneNumber}")
        Text("countryCode: ${userDetail.countryCode}")
        Text("dateBirth: ${userDetail.dateBirth}")
        Text("country: ${userDetail.country}")
        Text("province: ${userDetail.province}")
        Text("city: ${userDetail.city}")

    }
}

