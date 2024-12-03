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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kompasid.netdatalibrary.android.screens.AccountScreen
import com.kompasid.netdatalibrary.android.screens.AppIconUseCaseScreen
import com.kompasid.netdatalibrary.android.screens.ArticleListScreen
import com.kompasid.netdatalibrary.android.screens.AuthUseCaseScreen
import com.kompasid.netdatalibrary.android.screens.example.AboutScreen
import com.kompasid.netdatalibrary.android.screens.MainScreen
import com.kompasid.netdatalibrary.android.screens.Screens
import com.kompasid.netdatalibrary.android.screens.SettingsScreen


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
    NavHost(
        navController = navController,
        startDestination = Screens.LIST_APISERVICE_VIEW.route,
        modifier = modifier,
    ) {
        composable(Screens.LIST_APISERVICE_VIEW.route) {
            MainScreen(
                onAccountClick = { navController.navigate(Screens.ACCOUNT_VIEW.route) },
                onArticleListClick = { navController.navigate(Screens.ARTICLE_LIST_VIEW.route) },
                onSettinsClick = { navController.navigate(Screens.SETTINGS_VIEW.route) },
                onAuthUseCaseClick = { navController.navigate(Screens.AUTH_USECASE_VIEW.route) },
                onAppIconUseCaseClick = { navController.navigate(Screens.APPICON_USECASE_VIEW.route) },
            )
        }
//        composable(Screens.ARTICLES_EXAMPLE.route) {
//            ArticlesScreen(
//                onAboutButtonClick = { navController.navigate(Screens.ABOUT_DEVICE_EXAMPLE.route) },
//            )
//        }

        composable(Screens.ABOUT_DEVICE_EXAMPLE.route) {
            AboutScreen(
                onUpButtonClick = { navController.popBackStack() },
            )
        }

        composable(Screens.SETTINGS_VIEW.route) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() },
            )
        }

        composable(Screens.ARTICLE_LIST_VIEW.route) {
            ArticleListScreen(
                onBackClick = { navController.popBackStack() },
            )
        }

        composable(Screens.ACCOUNT_VIEW.route) {
            AccountScreen(
                onBackClick = { navController.popBackStack() },
            )
        }

        composable(Screens.AUTH_USECASE_VIEW.route) {
            AuthUseCaseScreen(
                onBackClick = { navController.popBackStack() },
            )
        }

        composable(Screens.APPICON_USECASE_VIEW.route) {
            AppIconUseCaseScreen(
                onBackClick = { navController.popBackStack() },
            )
        }
    }
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