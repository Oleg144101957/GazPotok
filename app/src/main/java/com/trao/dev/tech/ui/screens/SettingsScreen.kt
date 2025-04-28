package com.trao.dev.tech.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.trao.dev.R
import com.trao.dev.tech.navigation.ScreenRoutes
import com.trao.dev.tech.ui.elements.Background
import com.trao.dev.tech.ui.elements.IconButton
import com.trao.dev.tech.ui.elements.WhiteButton
import com.trao.dev.tech.util.CustomTabsUtil

@Composable
fun SettingsScreen(navController: NavController, innerPadding: PaddingValues) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Background()
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(modifier = Modifier.align(Alignment.Start)) {
                navController.popBackStack()
            }
            Spacer(Modifier.height(36.dp))
            WhiteButton(R.string.privacy_policy, modifier = Modifier) {
                CustomTabsUtil.openCustomTab(
                    context,
                    "https://telegra.ph/Privacy-Policy-for-TPAO-04-28"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            WhiteButton(R.string.about_btn, modifier = Modifier) {
                navController.navigate(ScreenRoutes.AboutScreen.route)
            }
        }
    }
}
