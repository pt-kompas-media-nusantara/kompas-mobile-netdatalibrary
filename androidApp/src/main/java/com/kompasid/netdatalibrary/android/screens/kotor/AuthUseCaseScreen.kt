package com.kompasid.netdatalibrary.android.screens.kotor


//@Composable
//fun AuthUseCaseScreen(
//    vm: AuthVM = koinViewModel(),
//    onBackClick: () -> Unit,
//) {
//    val accessToken by vm.accessToken.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .verticalScroll(rememberScrollState())
//    ) {
//        AppBackBar(onBackClick)
//
//        Text("AuthUseCaseScreen")
//        HorizontalDivider()
//
//        FilledButton("Login Anon", {
//            vm.postLoginGuest()
//        })
//
//        FilledButton("Login by Email", {
//            vm.postLoginByEmail()
//        })
//
//        FilledButton("Logout", {
//            vm.postLogout()
//        })
//
//        FilledButton("Decode JWT", {
//            vm.decodeJWT()
//        })
//
//        FilledButton("Cetak All Settings", {
//            vm.cetakAllSettings()
//        })
//
//        FilledButton("Cetak Access Token", {
//            vm.cetakAccessSettings()
//        })
//
//        Text("accessToken: $accessToken")
//    }
//}
