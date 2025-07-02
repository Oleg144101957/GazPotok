package com.sbe.rand.inve.sttwo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sbe.rand.inve.sttwo.domain.Menu
import com.sbe.rand.inve.sttwo.ui.screens.AboutScreen
import com.sbe.rand.inve.sttwo.ui.screens.HomeScreen
import com.sbe.rand.inve.sttwo.ui.screens.LoanCalculatorScreen
import com.sbe.rand.inve.sttwo.ui.screens.MyLoanTrackerScreen
import com.sbe.rand.inve.sttwo.ui.screens.NoNetworkScreen
import com.sbe.rand.inve.sttwo.ui.screens.OnboardingScreen
import com.sbe.rand.inve.sttwo.ui.screens.SettingsScreen
import com.sbe.rand.inve.sttwo.ui.screens.content.ContentScreen
import com.sbe.rand.inve.sttwo.ui.screens.currency_rates.CurrencyRatesScreen
import com.sbe.rand.inve.sttwo.ui.screens.investment_calculator.InvestmentCalculatorScreen
import com.sbe.rand.inve.sttwo.ui.screens.quiz.QuizScreen
import com.sbe.rand.inve.sttwo.ui.screens.quiz.ResultScreen
import com.sbe.rand.inve.sttwo.ui.screens.saving_goals.SavingsGoalsScreen
import com.sbe.rand.inve.sttwo.ui.screens.splash.SplashScreen

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