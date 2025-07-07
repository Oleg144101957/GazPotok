package com.qua.ntum.barh.at.ui.screens

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
import com.qua.ntum.barh.at.R
import com.qua.ntum.barh.at.navigation.ScreenRoutes
import com.qua.ntum.barh.at.ui.elements.Background
import com.qua.ntum.barh.at.ui.elements.GreenButton
import com.qua.ntum.barh.at.util.lockOrientation

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
                painterResource(R.drawable.logo),
                R.drawable.logo.toString(),
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

        GreenButton(R.string.modeling_gold_returns, modifier = Modifier) {
            navController.navigate("${ScreenRoutes.ModelingScreen.route}/gold")
        }

        Spacer(modifier = Modifier.height(16.dp))

        GreenButton(R.string.modeling_silver_returns, modifier = Modifier) {
            navController.navigate("${ScreenRoutes.ModelingScreen.route}/silver")
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreenButton(R.string.modeling_palladium_returns, modifier = Modifier) {
            navController.navigate("${ScreenRoutes.ModelingScreen.route}/palladium")
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreenButton(R.string.modeling_platinum_returns, modifier = Modifier) {
            navController.navigate("${ScreenRoutes.ModelingScreen.route}/platinum")
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreenButton(R.string.modeling_real_estate_returns, modifier = Modifier) {
            navController.navigate("${ScreenRoutes.ModelingScreen.route}/real_estate")
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreenButton(R.string.modeling_sp_500stocks_returns, modifier = Modifier) {
            navController.navigate("${ScreenRoutes.ModelingScreen.route}/s_and_p500")
        }
        Spacer(modifier = Modifier.height(16.dp))
        GreenButton(R.string.settings, modifier = Modifier) {
            navController.navigate(ScreenRoutes.SettingsScreen.route)
        }
    }
}
