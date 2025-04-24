package com.bayka.capitfin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bayka.capitfin.R
import com.bayka.capitfin.navigation.ScreenRoutes
import com.bayka.capitfin.ui.elements.Background
import com.bayka.capitfin.ui.elements.WhiteButton

@Composable
fun OnboardingScreen(navController: NavController, paddingValues: PaddingValues) {
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
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painterResource(R.drawable.logo), R.drawable.logo.toString(),
                modifier = Modifier.fillMaxWidth(0.8f),
                contentScale = ContentScale.FillWidth
            )
            Image(
                painterResource(R.drawable.ic_phone), R.drawable.ic_phone.toString(),
                modifier = Modifier.fillMaxWidth(0.9f),
                contentScale = ContentScale.FillWidth
            )
            Text(
                stringResource(R.string.welcome),
                style = TextStyle(
                    color = White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            WhiteButton(R.string.start) {
                navController.navigate(ScreenRoutes.HomeScreen.route)
            }
        }
    }
}