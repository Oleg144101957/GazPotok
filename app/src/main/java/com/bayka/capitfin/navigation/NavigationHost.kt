package com.bayka.capitfin.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bayka.capitfin.ui.screens.AboutScreen
import com.bayka.capitfin.ui.screens.HomeScreen
import com.bayka.capitfin.ui.screens.NoNetworkScreen
import com.bayka.capitfin.ui.screens.OnboardingScreen
import com.bayka.capitfin.ui.screens.SettingsScreen
import com.bayka.capitfin.ui.screens.SplashScreen

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

        composable(route = ScreenRoutes.NoNetworkScreen.route) {
            NoNetworkScreen(navController, innerPadding)
        }

        composable(route = ScreenRoutes.SettingsScreen.route) {
            SettingsScreen(navController, innerPadding)
        }

        composable(route = ScreenRoutes.OnboardingScreen.route) {
            OnboardingScreen(navController, innerPadding)
        }
    }
}