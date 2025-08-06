package ru.gz.po.tok41.ui.screens

import android.app.Activity
import android.content.pm.ActivityInfo
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
import ru.gz.po.tok41.R
import ru.gz.po.tok41.navigation.ScreenRoutes
import ru.gz.po.tok41.ui.elements.Background
import ru.gz.po.tok41.ui.elements.GreenButton
import ru.gz.po.tok41.ui.elements.IconButton
import ru.gz.po.tok41.util.CustomTabsUtil
import ru.gz.po.tok41.util.lockOrientation

@Composable
fun SettingsScreen(navController: NavController, innerPadding: PaddingValues) {
    val context = LocalContext.current
    val activity = context as? Activity
    activity?.lockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
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
            GreenButton(R.string.privacy_policy, modifier = Modifier) {
                CustomTabsUtil.openCustomTab(
                    context,
                    "https://sites.google.com/view/xczl2n9p/xczL2n9p"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            GreenButton(R.string.about_btn, modifier = Modifier) {
                navController.navigate(ScreenRoutes.AboutScreen.route)
            }

            Spacer(modifier = Modifier.height(16.dp))
            GreenButton(R.string.list_of_unwanted_expenses, modifier = Modifier) {
                navController.navigate(ScreenRoutes.UnwantedExpensesScreen.route)
            }
        }
    }
}
