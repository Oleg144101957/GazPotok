package com.bayka.capitfin.ui.screens.investment_calculator

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bayka.capitfin.R
import com.bayka.capitfin.ui.elements.Background
import com.bayka.capitfin.ui.elements.WhiteButton

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

    val metalPrices = mapOf(
        "gold" to viewModel.goldPrice,
        "silver" to viewModel.silverPrice,
        "platinum" to viewModel.platinumPrice
    )

    LaunchedEffect(Unit) {
        viewModel.loadData(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Background()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.investment_calculator),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                textAlign = TextAlign.Center
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = weightInput,
                    onValueChange = { weightInput = it },
                    label = { Text(stringResource(R.string.enter_weight), color = Color.White) },
                    textStyle = TextStyle(color = Color.White),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        disabledTextColor = Color.White.copy(alpha = 0.5f),
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.LightGray,
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("gold", "silver", "platinum").forEach { metal ->
                        val labelRes = when (metal) {
                            "gold" -> R.string.gold
                            "silver" -> R.string.silver
                            "platinum" -> R.string.platinum
                            else -> R.string.gold
                        }
                        Button(
                            onClick = { selectedMetal = metal },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedMetal == metal) MaterialTheme.colorScheme.primary else Color.LightGray
                            )
                        ) {
                            Text(stringResource(id = labelRes))
                        }
                    }
                }

                if (result.isNotEmpty()) {
                    Text(result, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                }
            }

            val priceResult = remember(weightInput, selectedMetal) {
                val weight = weightInput.toDoubleOrNull()
                val pricePer100g = metalPrices[selectedMetal] ?: 40.0
                weight?.let {
                    (pricePer100g / 100) * it
                }
            }

            WhiteButton(R.string.calculate) {
                result = if (priceResult != null) {
                    Log.v("123123"," result = $result")
                    context.getString(R.string.price_result, priceResult)
                } else {
                    context.getString(R.string.invalid_weight)
                }
            }

        }
    }
}

