package gf.na.chisle.nia.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import gf.na.chisle.nia.ui.screens.AboutScreen
import gf.na.chisle.nia.ui.screens.HomeScreen
import gf.na.chisle.nia.ui.screens.NoNetworkScreen
import gf.na.chisle.nia.ui.screens.SettingsScreen
import gf.na.chisle.nia.ui.screens.add_info.AddInfoScreen
import gf.na.chisle.nia.ui.screens.analytic.AnalyticsScreen
import gf.na.chisle.nia.ui.screens.content.ContentScreen
import gf.na.chisle.nia.ui.screens.history.HistoryScreen
import gf.na.chisle.nia.ui.screens.splash.SplashScreen

@Composable
fun NavigationHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = ScreenRoutes.SplashScreen.route) {
        composable(route = ScreenRoutes.SplashScreen.route) {
            SplashScreen(navController)
        }

        composable(route = ScreenRoutes.HomeScreen.route) {
            HomeScreen(navController, innerPadding)
        }

        composable(route = ScreenRoutes.AboutScreen.route) {
            AboutScreen(navController, innerPadding)
        }

        composable(
            route = "${ScreenRoutes.ContentScreen.route}/{url}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            ContentScreen(navController, innerPadding, url)
        }

        composable(route = ScreenRoutes.NoNetworkScreen.route) {
            NoNetworkScreen(navController, innerPadding)
        }

        composable(route = ScreenRoutes.HistoryScreen.route) {
            HistoryScreen(navController)
        }

        composable(route = ScreenRoutes.AnalyticScreen.route) {
            AnalyticsScreen(navController)
        }

        composable(route = ScreenRoutes.SettingsScreen.route) {
            SettingsScreen(navController, innerPadding)
        }

        composable(route = ScreenRoutes.AddInfoScreen.route) {
            AddInfoScreen(navController)
        }


    }
}