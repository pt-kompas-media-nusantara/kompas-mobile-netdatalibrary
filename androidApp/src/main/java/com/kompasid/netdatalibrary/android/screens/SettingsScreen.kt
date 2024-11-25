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
import com.kompasid.netdatalibrary.netData.presentation.articlesPresentation.ArticlesVM
import com.kompasid.netdatalibrary.android.AppBackBar
import org.koin.androidx.compose.getViewModel


@Composable
fun SettingsScreen(
    articlesVM: ArticlesVM = getViewModel(),
    onBackClick: () -> Unit,
) {
    val accessToken by articlesVM.accessToken.collectAsState()
    val refreshToken by articlesVM.refreshToken.collectAsState()
    val isVerified by articlesVM.isVerified.collectAsState()
    val deviceKeyId by articlesVM.deviceKeyId.collectAsState()
    val isSocial by articlesVM.isSocial.collectAsState()
    val userId by articlesVM.userId.collectAsState()
    val firstName by articlesVM.firstName.collectAsState()
    val lastName by articlesVM.lastName.collectAsState()
    val email by articlesVM.email.collectAsState()
    val userGuid by articlesVM.userGuid.collectAsState()
    val phoneNumber by articlesVM.phoneNumber.collectAsState()
    val countryCode by articlesVM.countryCode.collectAsState()
    val country by articlesVM.country.collectAsState()
    val province by articlesVM.province.collectAsState()
    val city by articlesVM.city.collectAsState()
    val membershipExpired by articlesVM.membershipExpired.collectAsState()
    val membershipActive by articlesVM.membershipActive.collectAsState()
    val membershipStartDate by articlesVM.membershipStartDate.collectAsState()
    val membershipEndDate by articlesVM.membershipEndDate.collectAsState()
    val membershipTotalGracePeriod by articlesVM.membershipTotalGracePeriod.collectAsState()
    val membershipGracePeriode by articlesVM.membershipGracePeriode.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppBackBar(onBackClick)

        Text("accessToken: $accessToken")
        HorizontalDivider()
        Text("refreshToken: $refreshToken")
        HorizontalDivider()
        Text("isVerified: $isVerified")
        HorizontalDivider()
        Text("deviceKeyId: $deviceKeyId")
        HorizontalDivider()
        Text("isSocial: $isSocial")
        HorizontalDivider()
        Text("userId: $userId")
        HorizontalDivider()
        Text("firstName: $firstName")
        HorizontalDivider()
        Text("lastName: $lastName")
        HorizontalDivider()
        Text("email: $email")
        HorizontalDivider()
        Text("userGuid: $userGuid")
        HorizontalDivider()
        Text("phoneNumber: $phoneNumber")
        HorizontalDivider()
        Text("countryCode: $countryCode")
        HorizontalDivider()
        Text("country: $country")
        HorizontalDivider()
        Text("province: $province")
        HorizontalDivider()
        Text("city: $city")
        HorizontalDivider()
        Text("membershipExpired: $membershipExpired")
        HorizontalDivider()
        Text("membershipActive: $membershipActive")
        HorizontalDivider()
        Text("membershipStartDate: $membershipStartDate")
        HorizontalDivider()
        Text("membershipEndDate: $membershipEndDate")
        HorizontalDivider()
        Text("membershipTotalGracePeriod: $membershipTotalGracePeriod")
        HorizontalDivider()
        Text("membershipGracePeriode: $membershipGracePeriode")
    }
}