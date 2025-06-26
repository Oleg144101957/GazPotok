package com.sbe.rand.inve.st.ui.screens.quiz

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sbe.rand.inve.st.R
import com.sbe.rand.inve.st.domain.Menu
import com.sbe.rand.inve.st.domain.QuizStatus
import com.sbe.rand.inve.st.domain.StatusState
import com.sbe.rand.inve.st.navigation.ScreenRoutes
import com.sbe.rand.inve.st.ui.elements.Background
import com.sbe.rand.inve.st.ui.elements.TextButton
import com.sbe.rand.inve.st.ui.theme.DarkBlue
import com.sbe.rand.inve.st.ui.theme.DefFont
import com.sbe.rand.inve.st.ui.theme.Green
import com.sbe.rand.inve.st.ui.theme.LightBlue
import com.sbe.rand.inve.st.util.lockOrientation

@Composable
fun QuizScreen(navController: NavController, paddingValues: PaddingValues, menuItem: String) {
    val item = Menu.valueOf(menuItem)
    val questions = item.listQuestions

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    val currentQuestion = questions[currentQuestionIndex]
    val results = remember { mutableStateListOf<Boolean>() }
    var status by remember { mutableStateOf(QuizStatus.QUESTION) }
    val context = LocalContext.current
    val activity = context as? Activity
    activity?.lockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    BackHandler { }

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
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(questions.size) { index ->
                    val state = when {
                        index == currentQuestionIndex && status == QuizStatus.QUESTION -> StatusState.CURRENT
                        index < results.size && results[index] -> StatusState.CORRECT
                        index < results.size && !results[index] -> StatusState.INCORRECT
                        else -> StatusState.DEFAULT
                    }
                    StatusText(
                        state,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Text(
                item.text,
                style = TextStyle(
                    color = DarkBlue,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = DefFont,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(Modifier.weight(1f))
            Image(
                painterResource(
                    when (status) {
                        QuizStatus.QUESTION -> R.drawable.sber_ic_question
                        QuizStatus.ANSWER -> when (results.last()) {
                            true -> R.drawable.sber_true
                            false -> R.drawable.sber_false
                        }
                    }
                ),
                R.drawable.sber_ic_question.toString()
            )
            Text(
                when (status) {
                    QuizStatus.QUESTION -> currentQuestion.question
                    QuizStatus.ANSWER -> when (results.last()) {
                        true -> currentQuestion.explanationIfCorrect
                        false -> currentQuestion.explanationIfWrong
                    }
                },
                style = TextStyle(
                    color = Black,
                    fontSize = 24.sp,
                    fontFamily = DefFont,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(Modifier.weight(3f))
            when (status) {
                QuizStatus.QUESTION -> {
                    Row(Modifier.fillMaxWidth()) {
                        TextButton("Ложь", color = Red, modifier = Modifier.weight(1f)) {
                            val isCorrect = currentQuestion.correctAnswer == false
                            results.add(isCorrect)

                            status = QuizStatus.ANSWER
                        }
                        Spacer(Modifier.width(8.dp))
                        TextButton("Правда", color = Green, modifier = Modifier.weight(1f)) {
                            val isCorrect = currentQuestion.correctAnswer == true
                            results.add(isCorrect)
                            status = QuizStatus.ANSWER
                        }
                    }
                }

                QuizStatus.ANSWER -> {
                    TextButton("Продолжить", modifier = Modifier.fillMaxWidth(0.9f)) {
                        if (currentQuestionIndex < questions.lastIndex) {
                            currentQuestionIndex++
                            status = QuizStatus.QUESTION
                        } else {
                            navController.navigate("${ScreenRoutes.ResultScreen.route}/${results.count { it }}")
                        }
                    }
                }
            }
            Spacer(Modifier.weight(1f))
        }
    }
}

@Composable
fun StatusText(state: StatusState, modifier: Modifier) {
    val backgroundColor = when (state) {
        StatusState.DEFAULT -> LightBlue
        StatusState.CURRENT -> Blue
        StatusState.CORRECT -> Green
        StatusState.INCORRECT -> Red
    }
    Box(
        modifier
            .height(10.dp)
            .background(backgroundColor, RoundedCornerShape(16.dp))
    )
}
