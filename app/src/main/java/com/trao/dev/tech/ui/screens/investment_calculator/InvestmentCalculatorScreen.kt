package com.trao.dev.tech.ui.screens.investment_calculator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.trao.dev.R
import com.trao.dev.tech.ui.elements.Background
import com.trao.dev.tech.ui.elements.IconButton
import com.trao.dev.tech.ui.elements.WhiteButton
import com.trao.dev.tech.ui.theme.Red

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White.copy(0.5f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(modifier = Modifier.align(Alignment.Start)) {
                navController.popBackStack()
            }
            Spacer(Modifier.height(36.dp))
            OutlinedTextField(
                value = weightInput,
                onValueChange = { weightInput = it },
                label = { Text(stringResource(R.string.enter_weight), color = White) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                    focusedContainerColor = Transparent,
                    unfocusedContainerColor = Transparent,
                    cursorColor = White,
                    focusedIndicatorColor = Red,
                    unfocusedIndicatorColor = Red
                ),
                textStyle = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
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
                            containerColor = if (selectedMetal == metal) Red else White,
                            contentColor = if (selectedMetal == metal) White else Red
                        ),
                        elevation = ButtonDefaults.buttonElevation(8.dp),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(stringResource(id = labelRes))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            val priceResult = remember(weightInput, selectedMetal) {
                val weight = weightInput.toDoubleOrNull()
                val pricePer100g = metalPrices[selectedMetal] ?: 40.0
                weight?.let {
                    (pricePer100g / 100) * it
                }
            }
            WhiteButton(R.string.calculate) {
                result = if (priceResult != null) {
                    Log.v("123123", " result = $result")
                    context.getString(R.string.price_result, priceResult)
                } else {
                    context.getString(R.string.invalid_weight)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (result.isNotEmpty()) {
                Text(
                    result,
                    style = TextStyle(
                        color = White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}
