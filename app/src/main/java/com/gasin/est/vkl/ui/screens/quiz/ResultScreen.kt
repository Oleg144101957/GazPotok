package com.gasin.est.vkl.ui.screens.quiz

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gasin.est.vkl.R
import com.gasin.est.vkl.navigation.ScreenRoutes
import com.gasin.est.vkl.ui.elements.Background
import com.gasin.est.vkl.ui.elements.TextButton
import com.gasin.est.vkl.ui.theme.DarkBlue
import com.gasin.est.vkl.ui.theme.DefFont
import com.gasin.est.vkl.util.lockOrientation

@Composable
fun ResultScreen(navController: NavController, paddingValues: PaddingValues, count: Int) {
    BackHandler { }
    val context = LocalContext.current
    val activity = context as? Activity
    activity?.lockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Background()
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f))
            Image(
                painterResource(R.drawable.sber_result), R.drawable.sber_result.toString(),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.weight(1f))
            Text(
                when (count) {
                    0, 1, 2, 3 -> "Есть над чем поработать!"
                    4, 5 -> "Отличный результат!"
                    6 -> "Вы — финансовый мастер!"
                    else -> ""
                },
                style = TextStyle(
                    color = DarkBlue,
                    fontSize = 24.sp,
                    fontFamily = DefFont,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                when (count) {
                    0, 1, 2, 3 -> "Вы сделали первые шаги — продолжайте! Знание финансов защитит Вас от ошибок и потерь."
                    4, 5 -> "Вы хорошо разбираетесь в теме. Немного практики — и уровень эксперта будет у Вас в кармане."
                    6 -> "Поздравляем! Вы отлично ориентируетесь в мире денег. Продолжайте в том же духе!"
                    else -> ""
                },
                style = TextStyle(
                    color = Black,
                    fontSize = 20.sp,
                    fontFamily = DefFont,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.weight(1f))
            TextButton("Главное меню", modifier = Modifier.fillMaxWidth(0.9f)) {
                navController.navigate(ScreenRoutes.HomeScreen.route)
            }
            Spacer(Modifier.weight(1f))
        }
    }
}