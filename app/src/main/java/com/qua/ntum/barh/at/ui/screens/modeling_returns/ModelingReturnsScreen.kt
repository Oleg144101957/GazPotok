package com.qua.ntum.barh.at.ui.screens.modeling_returns

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.qua.ntum.barh.at.R
import com.qua.ntum.barh.at.ui.elements.Background
import com.qua.ntum.barh.at.ui.elements.CircularSlider
import com.qua.ntum.barh.at.ui.elements.CustomOutlinedTextField
import com.qua.ntum.barh.at.ui.elements.GreenButton

@Composable
fun ModelingReturnsScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    type: String,
    viewModel: ModelingViewModel = hiltViewModel()
) {
    val screenName = when (type) {
        "gold" -> R.string.modeling_gold_returns
        "silver" -> R.string.modeling_silver_returns
        "platinum" -> R.string.modeling_platinum_returns
        "palladium" -> R.string.modeling_palladium_returns
        "s_and_p500" -> R.string.modeling_sp_500stocks_returns
        "real_estate" -> R.string.modeling_real_estate_returns
        else -> R.string.modeling_gold_returns
    }

    val selectedMonths by viewModel.selectedMonths.collectAsState()
    val calculatedReturn by viewModel.calculatedReturn.collectAsState()

    val context = LocalContext.current

    val profitPrefix = stringResource(id = R.string.profit_prefix)
    val unknownProfit = stringResource(id = R.string.unknown_profit)
    val inputLabel = stringResource(id = R.string.input_label)

    var amountInput by remember { mutableStateOf("") }
    val profit = remember(calculatedReturn, amountInput) {
        val amount = amountInput.toFloatOrNull()
        if (calculatedReturn != null && amount != null) {
            calculatedReturn!! * amount / 100f
        } else null
    }

    LaunchedEffect(Unit) {
        viewModel.loadReturnsFor(context, type)
        // Инициализируем selectedMonths, если нужно
        if (selectedMonths == null) {
            viewModel.setSelectedMonths(1)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Background()

        Box(
            Modifier
                .fillMaxSize()
                .padding(top = 12.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                stringResource(screenName),
                color = Color.Black,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = profit?.let { "$profitPrefix${"%.2f".format(it)}" } ?: unknownProfit,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Circular Slider для выбора месяцев
            selectedMonths?.let { months ->
                CircularSlider(
                    value = months,
                    onValueChange = { newValue -> viewModel.setSelectedMonths(newValue) }
                )
            } ?: Text("Загрузка...", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(24.dp))

            CustomOutlinedTextField(
                value = amountInput,
                onValueChange = { amountInput = it },
                labelText = inputLabel,
                keyboardType = KeyboardType.Number,
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            GreenButton(textRes = R.string.calculate_button) {
                if (amountInput.isBlank()) {
                    Toast.makeText(context, "Введите данные", Toast.LENGTH_SHORT).show()
                    return@GreenButton
                }
                val amount = amountInput.toFloatOrNull()
                if (amount != null) {
                    viewModel.calculateReturn(amount)
                } else {
                    Toast.makeText(context, "Некорректное число", Toast.LENGTH_SHORT).show()
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            if (calculatedReturn != null && selectedMonths != null) {
                Text(
                    text = stringResource(
                        id = R.string.return_result,
                        selectedMonths!!,
                        calculatedReturn!!
                    ),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }
    }
}

