package com.sbera.sschet.ui.screens.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sbera.sschet.R
import com.sbera.sschet.navigation.ScreenRoutes
import com.sbera.sschet.ui.elements.Background
import com.sbera.sschet.ui.elements.BallScaleMultipleIndicator
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    BackHandler { }

    LaunchedEffect(Unit) {
        delay(1500)
        navController.navigate(ScreenRoutes.OnboardingScreen.route)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Background()
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(R.drawable.sberrasschet_men),
                R.drawable.sberrasschet_men.toString(),
                modifier = Modifier.fillMaxWidth(0.8f),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(128.dp))
            BallScaleMultipleIndicator()
        }
    }
}
