package com.trao.dev.tech.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.trao.dev.tech.ui.screens.AboutScreen
import com.trao.dev.tech.ui.screens.HomeScreen
import com.trao.dev.tech.ui.screens.LoanCalculatorScreen
import com.trao.dev.tech.ui.screens.MyLoanTrackerScreen
import com.trao.dev.tech.ui.screens.NoNetworkScreen
import com.trao.dev.tech.ui.screens.OnboardingScreen
import com.trao.dev.tech.ui.screens.SettingsScreen
import com.trao.dev.tech.ui.screens.SplashScreen
import com.trao.dev.tech.ui.screens.currency_rates.CurrencyRatesScreen
import com.trao.dev.tech.ui.screens.investment_calculator.InvestmentCalculatorScreen
import com.trao.dev.tech.ui.screens.saving_goals.SavingsGoalsScreen

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

        composable(route = ScreenRoutes.CurrencyRatesScreen.route) {
            CurrencyRatesScreen(navController, innerPadding)
        }

        composable(route = ScreenRoutes.InvestmentCalculatorScreen.route) {
            InvestmentCalculatorScreen(navController, innerPadding)
        }

        composable(route = ScreenRoutes.LoanCalculatorScreen.route) {
            LoanCalculatorScreen(navController, innerPadding)
        }

        composable(route = ScreenRoutes.MyLoanTrackerScreen.route) {
            MyLoanTrackerScreen(navController, innerPadding)
        }

        composable(route = ScreenRoutes.SavingsGoalsScreen.route) {
            SavingsGoalsScreen(navController, innerPadding)
        }
    }
}