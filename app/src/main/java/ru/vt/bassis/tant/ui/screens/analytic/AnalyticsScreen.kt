package ru.vt.bassis.tant.ui.screens.analytic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.vt.bassis.tant.R
import ru.vt.bassis.tant.ui.elements.Background
import ru.vt.bassis.tant.ui.elements.GreenButton
import ru.vt.bassis.tant.ui.screens.add_info.ExpensesViewModel
import java.util.Calendar

@Composable
fun AnalyticsScreen(navController: NavController, viewModel: ExpensesViewModel = hiltViewModel()) {
    val expenses = viewModel.expenses
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val monthlyExpenses = expenses.filter {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = it.timestamp
        calendar.get(Calendar.MONTH) == currentMonth
    }

    val total = monthlyExpenses.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }

    val categoryMap = monthlyExpenses.groupBy { it.category }
        .mapValues { entry ->
            entry.value.sumOf { it.amount.toDoubleOrNull() ?: 0.0 }
        }

    val colors = listOf(
        Color(0xFF4CAF50),
        Color(0xFF2196F3),
        Color(0xFFFFC107),
        Color(0xFFF44336),
        Color(0xFF9C27B0),
        Color.Gray
    )
    val slices = categoryMap.entries.mapIndexed { index, (category, sum) ->
        val sumFloat = sum.toFloat()
        val percentage = if (total == 0.0) 0f else (sumFloat / total.toFloat() * 360f)
        PieChartSlice(category, sumFloat, colors[index % colors.size], percentage)
    }

    Background()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Анализ трат за месяц",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Ваши расходы в этом месяце в категориях",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    var startAngle = -90f
                    slices.forEach { slice ->
                        drawArc(
                            color = slice.color,
                            startAngle = startAngle,
                            sweepAngle = slice.percentage,
                            useCenter = true
                        )
                        startAngle += slice.percentage
                    }
                }
                Text(
                    text = "%.0f ₽".format(total),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7)),
                modifier = Modifier
                    .padding(8.dp)
                    .widthIn(min = 150.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "По категориям",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    slices.forEach { slice ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(slice.color, shape = CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${slice.category}: ${if (total == 0.0) 0 else (slice.amount / total * 100).toInt()}%",
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        GreenButton(R.string.back, modifier = Modifier.fillMaxWidth()) {
            navController.popBackStack()
        }
    }
}

data class PieChartSlice(
    val category: String,
    val amount: Float,
    val color: Color,
    val percentage: Float
)
