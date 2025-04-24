package com.bayka.capitfin.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bayka.capitfin.R
import com.bayka.capitfin.navigation.ScreenRoutes
import com.bayka.capitfin.ui.elements.Background
import com.bayka.capitfin.ui.elements.RedButton

@Composable
fun HomeScreen(navController: NavHostController, innerPadding: PaddingValues) {
    BackHandler { }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Background()
        Menu(navController)
    }
}

@Composable
fun Menu(navController: NavHostController) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        RedButton(R.string.currency_rates, modifier = Modifier) {
            navController.navigate(ScreenRoutes.CurrencyRatesScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        RedButton(R.string.loan_calculator, modifier = Modifier) {
            navController.navigate(ScreenRoutes.LoanCalculatorScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        RedButton(R.string.my_loan_tracker, modifier = Modifier) {
            navController.navigate(ScreenRoutes.MyLoanTrackerScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        RedButton(R.string.savings_goals, modifier = Modifier) {
            navController.navigate(ScreenRoutes.SavingsGoalsScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        RedButton(R.string.investment_calculator, modifier = Modifier) {
            navController.navigate(ScreenRoutes.InvestmentCalculatorScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        RedButton(R.string.settings, modifier = Modifier) {
            navController.navigate(ScreenRoutes.SettingsScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        RedButton(R.string.about_btn, modifier = Modifier) {
            navController.navigate(ScreenRoutes.AboutScreen.route)
        }
    }
}
