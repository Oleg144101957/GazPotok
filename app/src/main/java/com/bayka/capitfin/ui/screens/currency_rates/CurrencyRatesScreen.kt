package com.bayka.capitfin.ui.screens.currency_rates

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
import androidx.compose.material3.MaterialTheme
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
import com.bayka.capitfin.R
import com.bayka.capitfin.ui.elements.Background
import com.bayka.capitfin.ui.elements.IconButton
import com.bayka.capitfin.ui.elements.WhiteButton
import com.bayka.capitfin.ui.theme.Red

@Composable
fun CurrencyRatesScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: CurrencyRatesViewModel = hiltViewModel()
) {
    val currencies = listOf("USD", "EUR", "TRY")
    var fromCurrency by remember { mutableStateOf("TRY") }
    var toCurrency by remember { mutableStateOf("USD") }
    var amountInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val exchangeRates = mapOf(
        "USD" to viewModel.dollarPrice,
        "EUR" to viewModel.euroPrice,
        "TRY" to viewModel.liraPrice
    )
    val context = LocalContext.current

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
                value = amountInput,
                onValueChange = { amountInput = it },
                label = {
                    Text(
                        stringResource(R.string.enter_amount),
                        fontSize = 18.sp,
                        color = Red
                    )
                },
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
                currencies.forEach { currency ->
                    Button(
                        onClick = { fromCurrency = currency },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (fromCurrency == currency) Red else White,
                            contentColor = if (fromCurrency == currency) White else Red
                        ),
                        elevation = ButtonDefaults.buttonElevation(8.dp),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(currency)
                    }
                }
            }

            Text("→", color = White, style = MaterialTheme.typography.titleLarge)

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                currencies.forEach { currency ->
                    Button(
                        onClick = { toCurrency = currency },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (toCurrency == currency) Red else White,
                            contentColor = if (toCurrency == currency) White else Red
                        ),
                        elevation = ButtonDefaults.buttonElevation(8.dp),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(currency)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            WhiteButton(R.string.convert) {
                val amount = amountInput.toDoubleOrNull()
                result = if (amount != null) {
                    val fromRate = exchangeRates[fromCurrency] ?: 1.0
                    val toRate = exchangeRates[toCurrency] ?: 1.0
                    val converted = amount * fromRate / toRate
                    Log.v("123123", "$converted")
                    context.getString(R.string.convert_result, converted)
                } else {
                    context.getString(R.string.invalid_amount)
                }
            }

            if (result.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
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


