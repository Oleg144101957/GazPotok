package com.trao.dev.tech.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trao.dev.R
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
        Background(R.drawable.bg_loading)
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(R.drawable.logo), R.drawable.logo.toString(),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(16.dp))
            PulseAnimation()
        }
    }
}
