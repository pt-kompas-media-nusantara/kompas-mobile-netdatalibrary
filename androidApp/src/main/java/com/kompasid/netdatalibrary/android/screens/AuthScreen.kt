package com.kompasid.netdatalibrary.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            vm.loginByEmailTest()
        }
        FilledButton("removaAll") {
            vm.removaAllTest()
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()

//        Text("login")
//        Text("accessToken: ${login.accessToken}")
//        Text("refreshToken: ${login.refreshToken}")
//
//        HorizontalDivider()
//        Spacer(modifier = Modifier.height(16.dp))
//        HorizontalDivider()
//
//        Text("userDetail")
//        Text("idGender: ${userDetail.idGender}")
//        Text("gender: ${userDetail.gender}")
//        Text("userId: ${userDetail.userId}")
//        Text("firstName: ${userDetail.firstName}")
//        Text("lastName: ${userDetail.lastName}")
//        Text("email: ${userDetail.email}")
//        Text("userGuid: ${userDetail.userGuid}")
//        Text("isActive: ${userDetail.isActive}")
//        Text("isVerified: ${userDetail.userStatus.isVerified}")
//        Text("phoneVerified: ${userDetail.userStatus.phoneVerified}")
//        Text("phoneNumber: ${userDetail.phoneNumber}")
//        Text("countryCode: ${userDetail.countryCode}")
//        Text("dateBirth: ${userDetail.dateBirth}")
//        Text("country: ${userDetail.country}")
//        Text("province: ${userDetail.province}")
//        Text("city: ${userDetail.city}")
//
//        HorizontalDivider()
//        Spacer(modifier = Modifier.height(16.dp))
//        HorizontalDivider()
//
//        Text("userHistoryMembership")
//        Text("expired: ${userHistoryMembership.user.expired}")
//        Text("isActive: ${userHistoryMembership.user.isActive}")
//        Text("startDate: ${userHistoryMembership.user.startDate}")
//        Text("endDate: ${userHistoryMembership.user.endDate}")
//        Text("totalGracePeriod: ${userHistoryMembership.user.totalGracePeriod}")
//        Text("gracePeriod: ${userHistoryMembership.user.gracePeriod}")
//        if (userHistoryMembership.active.isNotEmpty()) {
//            LazyColumn {
//                items(userHistoryMembership.active) { membership ->
//                    MembershipItem(membership)
//                }
//            }
//        } else {
//            Text(text = "No active memberships", color = Color.Gray)
//        }
//        if (userHistoryMembership.expired.isNotEmpty()) {
//            LazyColumn {
//                items(userHistoryMembership.expired) { membership ->
//                    MembershipItem(membership)
//                }
//            }
//        } else {
//            Text(text = "No active memberships", color = Color.Gray)
//        }
    }
}

