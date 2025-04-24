package com.bayka.capitfin.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bayka.capitfin.ui.screens.AboutScreen
import com.bayka.capitfin.ui.screens.currency_rates.CurrencyRatesScreen
import com.bayka.capitfin.ui.screens.HomeScreen
import com.bayka.capitfin.ui.screens.investment_calculator.InvestmentCalculatorScreen
import com.bayka.capitfin.ui.screens.LoanCalculatorScreen
import com.bayka.capitfin.ui.screens.MyLoanTrackerScreen
import com.bayka.capitfin.ui.screens.NoNetworkScreen
import com.bayka.capitfin.ui.screens.OnboardingScreen
import com.bayka.capitfin.ui.screens.saving_goals.SavingsGoalsScreen
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