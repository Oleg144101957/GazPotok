package com.sbe.rand.inve.sttwo.ui.screens

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sbe.rand.inve.sttwo.R
import com.sbe.rand.inve.sttwo.domain.Menu
import com.sbe.rand.inve.sttwo.navigation.ScreenRoutes
import com.sbe.rand.inve.sttwo.ui.elements.Background
import com.sbe.rand.inve.sttwo.ui.elements.GreenButton
import com.sbe.rand.inve.sttwo.util.lockOrientation

@Composable
fun HomeScreen(navController: NavHostController, innerPadding: PaddingValues) {

    val context = LocalContext.current
    val activity = context as? Activity
    activity?.lockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    BackHandler { }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Background()
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))
            Image(
                painterResource(R.drawable.sberrasschet_logo),
                R.drawable.sberrasschet_logo.toString(),
                modifier = Modifier.fillMaxWidth(0.3f),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(32.dp))
            Menu(navController)
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

        val randomMenu = Menu.entries.random()

        GreenButton(R.string.quiz, modifier = Modifier) {
            navController.navigate("${ScreenRoutes.QuizScreen.route}/${randomMenu.name}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        GreenButton(R.string.currency_rates, modifier = Modifier) {
            navController.navigate(ScreenRoutes.CurrencyRatesScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreenButton(R.string.loan_calculator, modifier = Modifier) {
            navController.navigate(ScreenRoutes.LoanCalculatorScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreenButton(R.string.my_loan_tracker, modifier = Modifier) {
            navController.navigate(ScreenRoutes.MyLoanTrackerScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreenButton(R.string.savings_goals, modifier = Modifier) {
            navController.navigate(ScreenRoutes.SavingsGoalsScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreenButton(R.string.investment_calculator, modifier = Modifier) {
            navController.navigate(ScreenRoutes.InvestmentCalculatorScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreenButton(R.string.settings, modifier = Modifier) {
            navController.navigate(ScreenRoutes.SettingsScreen.route)
        }
    }
}
