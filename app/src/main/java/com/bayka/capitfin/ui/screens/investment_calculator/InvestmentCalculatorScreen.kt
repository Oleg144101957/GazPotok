package com.bayka.capitfin.ui.screens.investment_calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun InvestmentCalculatorScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: InvestmentCalculatorViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var weightInput by remember { mutableStateOf("") }
    var selectedMetal by remember { mutableStateOf("gold") }
    var result by remember { mutableStateOf("") }

    // Предположим, данные уже загружены, и их можно получить напрямую
    val metalPrices =
        remember { mutableStateMapOf("gold" to 40.0, "silver" to 40.0, "platinum" to 40.0) }

    LaunchedEffect(Unit) {
        viewModel.loadData(context)
        // Здесь добавьте механизм получения и установки цен из ViewModel, как State
        // Например: metalPrices["gold"] = viewModel.goldPrice.toDouble()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Калькулятор инвестиций", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it },
            label = { Text("Введите вес в граммах") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("gold", "silver", "platinum").forEach { metal ->
                Button(
                    onClick = { selectedMetal = metal },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedMetal == metal) MaterialTheme.colorScheme.primary else Color.LightGray
                    )
                ) {
                    Text(metal.capitalize())
                }
            }
        }

        Button(onClick = {
            val weight = weightInput.toDoubleOrNull()
            val pricePer100g = metalPrices[selectedMetal] ?: 40.0
            if (weight != null) {
                result = "Стоимость: ${(pricePer100g / 100) * weight} ₽"
            } else {
                result = "Пожалуйста, введите корректный вес"
            }
        }) {
            Text("Рассчитать")
        }

        if (result.isNotEmpty()) {
            Text(result, style = MaterialTheme.typography.bodyLarge)
        }
    }
}