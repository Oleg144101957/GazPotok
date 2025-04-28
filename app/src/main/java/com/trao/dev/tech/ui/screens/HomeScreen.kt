package com.trao.dev.tech.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trao.dev.tech.R
import com.trao.dev.tech.navigation.ScreenRoutes
import com.trao.dev.tech.ui.elements.Background
import com.trao.dev.tech.ui.elements.RedButton

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White.copy(0.5f))
        )
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.trao_flag), R.drawable.trao_flag.toString(),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(32.dp))
            Menu(navController)
            Spacer(Modifier.height(32.dp))
            Image(
                painterResource(R.drawable.trao_graphic), R.drawable.trao_graphic.toString(),
                modifier = Modifier.fillMaxWidth(0.8f),
                contentScale = ContentScale.FillWidth
            )
        }
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
    }
}
