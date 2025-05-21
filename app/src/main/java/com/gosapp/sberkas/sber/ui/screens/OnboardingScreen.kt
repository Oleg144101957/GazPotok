package com.gosapp.sberkas.sber.ui.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gosapp.sberkas.sber.R
import com.gosapp.sberkas.sber.navigation.ScreenRoutes
import com.gosapp.sberkas.sber.ui.elements.Background
import com.gosapp.sberkas.sber.ui.elements.GreenButton
import com.gosapp.sberkas.sber.util.lockOrientation

@Composable
fun OnboardingScreen(navController: NavController, paddingValues: PaddingValues) {

    BackHandler {}
    val context = LocalContext.current
    val activity = context as? Activity
    activity?.lockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    Box(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Background()
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painterResource(R.drawable.sberrasschet_men),
                R.drawable.sberrasschet_men.toString(),
                modifier = Modifier.fillMaxWidth(0.8f),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(64.dp))
            Text(
                stringResource(R.string.welcome),
                style = TextStyle(
                    color = White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.height(64.dp))
            GreenButton(R.string.start) {
                navController.navigate(ScreenRoutes.HomeScreen.route)
            }
            Spacer(Modifier.height(64.dp))
        }
    }
}