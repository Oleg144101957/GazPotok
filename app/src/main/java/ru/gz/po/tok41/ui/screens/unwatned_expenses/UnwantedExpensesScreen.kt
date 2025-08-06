package ru.gz.po.tok41.ui.screens.unwatned_expenses

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.gz.po.tok41.R
import ru.gz.po.tok41.ui.elements.Background
import ru.gz.po.tok41.ui.elements.CustomOutlinedTextField
import ru.gz.po.tok41.ui.elements.GreenButton
import ru.gz.po.tok41.util.lockOrientation

@Composable
fun UnwantedExpensesScreen(
    navController: NavController,
    viewModel: UnwantedExpensesViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context as? Activity
    activity?.lockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val unwantedExpenses = viewModel.unwantedExpenses
    var newExpense by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(),
        contentAlignment = Alignment.Center
    ) {
        Background()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ваш черный список трат",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )

            CustomOutlinedTextField(
                value = newExpense,
                onValueChange = { newExpense = it },
                labelText = "Добавьте нежелательную трату"
            )

            GreenButton(
                textRes = R.string.add_expense, // В strings.xml: <string name="add_expense">Добавить</string>
                onClick = {
                    if (newExpense.isNotBlank()) {
                        viewModel.addExpense(newExpense)
                        newExpense = ""
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            unwantedExpenses.forEach { item ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(item, color = Color.Black)
                        IconButton(onClick = { viewModel.removeExpense(item) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Удалить", tint = Color.Red)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            GreenButton(
                textRes = R.string.back,
                onClick = { navController.popBackStack() }
            )
        }
    }
}

