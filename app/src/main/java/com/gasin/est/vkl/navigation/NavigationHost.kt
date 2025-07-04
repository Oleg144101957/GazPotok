package com.gasin.est.vkl.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gasin.est.vkl.domain.Menu
import com.gasin.est.vkl.ui.screens.AboutScreen
import com.gasin.est.vkl.ui.screens.HomeScreen
import com.gasin.est.vkl.ui.screens.LoanCalculatorScreen
import com.gasin.est.vkl.ui.screens.MyLoanTrackerScreen
import com.gasin.est.vkl.ui.screens.NoNetworkScreen
import com.gasin.est.vkl.ui.screens.OnboardingScreen
import com.gasin.est.vkl.ui.screens.SettingsScreen
import com.gasin.est.vkl.ui.screens.content.ContentScreen
import com.gasin.est.vkl.ui.screens.currency_rates.CurrencyRatesScreen
import com.gasin.est.vkl.ui.screens.investment_calculator.InvestmentCalculatorScreen
import com.gasin.est.vkl.ui.screens.quiz.QuizScreen
import com.gasin.est.vkl.ui.screens.quiz.ResultScreen
import com.gasin.est.vkl.ui.screens.saving_goals.SavingsGoalsScreen
import com.gasin.est.vkl.ui.screens.splash.SplashScreen

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

        composable(
            route = "${ScreenRoutes.ResultScreen.route}/{count}",
            arguments = listOf(
                navArgument("count") {
                    type = NavType.IntType
                }
            )
        ) {
            ResultScreen(
                navController,
                innerPadding,
                it.arguments?.getInt("count") ?: 1
            )
        }

        composable(
            route = "${ScreenRoutes.QuizScreen.route}/{menuItem}",
            arguments = listOf(
                navArgument("menuItem") {
                    type = NavType.StringType
                }
            )
        ) {
            QuizScreen(
                navController,
                innerPadding,
                it.arguments?.getString("menuItem") ?: Menu.MONEY_HISTORY.toString()
            )
        }
    }
}