package com.trao.dev.tech.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.trao.dev.tech.R
import com.trao.dev.tech.navigation.ScreenRoutes
import com.trao.dev.tech.ui.elements.Background
import com.trao.dev.tech.ui.elements.PulseAnimation
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    BackHandler { }

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(ScreenRoutes.OnboardingScreen.route)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Background(R.drawable.trao_bg_loading)
        PulseAnimation()
    }
}
