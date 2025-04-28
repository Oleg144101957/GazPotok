package com.trao.dev.tech.ui.screens

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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trao.dev.tech.R
import com.trao.dev.tech.navigation.ScreenRoutes
import com.trao.dev.tech.ui.elements.Background
import com.trao.dev.tech.ui.elements.RedButton

@Composable
fun OnboardingScreen(navController: NavController, paddingValues: PaddingValues) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Background(R.drawable.trao_bg_onboarding)
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                stringResource(R.string.welcome),
                style = TextStyle(
                    color = Black,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.height(64.dp))
            RedButton(R.string.start) {
                navController.navigate(ScreenRoutes.HomeScreen.route)
            }
            Spacer(Modifier.height(64.dp))
            Image(
                painterResource(R.drawable.trao_turkey_flag),
                R.drawable.trao_turkey_flag.toString(),
                modifier = Modifier
                    .fillMaxWidth(0.2f),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(64.dp))
        }
    }
}