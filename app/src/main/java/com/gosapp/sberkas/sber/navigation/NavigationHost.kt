package com.gosapp.sberkas.sber.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gosapp.sberkas.sber.ui.screens.content.ContentScreen
import com.gosapp.sberkas.sber.ui.screens.AboutScreen
import com.gosapp.sberkas.sber.ui.screens.HomeScreen
import com.gosapp.sberkas.sber.ui.screens.LoanCalculatorScreen
import com.gosapp.sberkas.sber.ui.screens.MyLoanTrackerScreen
import com.gosapp.sberkas.sber.ui.screens.NoNetworkScreen
import com.gosapp.sberkas.sber.ui.screens.OnboardingScreen
import com.gosapp.sberkas.sber.ui.screens.SettingsScreen
import com.gosapp.sberkas.sber.ui.screens.currency_rates.CurrencyRatesScreen
import com.gosapp.sberkas.sber.ui.screens.investment_calculator.InvestmentCalculatorScreen
import com.gosapp.sberkas.sber.ui.screens.saving_goals.SavingsGoalsScreen
import com.gosapp.sberkas.sber.ui.screens.splash.SplashScreen

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


        composable(route = ScreenRoutes.ContentScreen.route) {
            ContentScreen(navController)
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