package com.kompasid.netdatalibrary.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
//    NavHost(
//        navController = navController,
//        startDestination = Screens.LIST_APISERVICE_VIEW.route,
//        modifier = modifier,
//    ) {
//        composable(Screens.LIST_APISERVICE_VIEW.route) {
//            MainScreen(
//                onAccountClick = { navController.navigate(Screens.ACCOUNT_VIEW.route) },
//                onArticleListClick = { navController.navigate(Screens.ARTICLE_LIST_VIEW.route) },
//                onSettinsClick = { navController.navigate(Screens.SETTINGS_VIEW.route) },
//                onAuthUseCaseClick = { navController.navigate(Screens.AUTH_USECASE_VIEW.route) },
//                onAppIconUseCaseClick = { navController.navigate(Screens.APPICON_USECASE_VIEW.route) },
//                onMyAccountUseCaseScreenClick = { navController.navigate(Screens.MY_ACCOUNT_VIEW.route) },
//            )
//        }
////        composable(Screens.ARTICLES_EXAMPLE.route) {
////            ArticlesScreen(
////                onAboutButtonClick = { navController.navigate(Screens.ABOUT_DEVICE_EXAMPLE.route) },
////            )
////        }
//
//        composable(Screens.ABOUT_DEVICE_EXAMPLE.route) {
//            AboutScreen(
//                onUpButtonClick = { navController.popBackStack() },
//            )
//        }
//
//        composable(Screens.SETTINGS_VIEW.route) {
//            SettingsScreen(
//                onBackClick = { navController.popBackStack() },
//            )
//        }
//
//        composable(Screens.ACCOUNT_VIEW.route) {
//            AccountScreen(
//                onBackClick = { navController.popBackStack() },
//            )
//        }
//
//        composable(Screens.AUTH_USECASE_VIEW.route) {
//            AuthUseCaseScreen(
//                onBackClick = { navController.popBackStack() },
//            )
//        }
//
//        composable(Screens.MY_ACCOUNT_VIEW.route) {
//            MyAccountUseCaseScreen(
//                onBackClick = { navController.popBackStack() },
//            )
//        }
//
//        composable(Screens.APPICON_USECASE_VIEW.route) {
//            AppIconUseCaseScreen(
//                onBackClick = { navController.popBackStack() },
//            )
//        }
//    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBackBar(
    onAboutButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Back") },
        actions = {
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "",
                )
            }
        }
    )
}