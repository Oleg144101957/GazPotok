package com.sbe.rand.inve.sttwo.ui.screens.currency_rates

import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.sbe.rand.inve.sttwo.R
import com.sbe.rand.inve.sttwo.ui.elements.Background
import com.sbe.rand.inve.sttwo.ui.elements.CustomOutlinedTextField
import com.sbe.rand.inve.sttwo.ui.elements.IconButton
import com.sbe.rand.inve.sttwo.ui.elements.WhiteButton
import com.sbe.rand.inve.sttwo.ui.theme.Green
import com.sbe.rand.inve.sttwo.util.lockOrientation

@Composable
fun CurrencyRatesScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: CurrencyRatesViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val activity = context as? Activity
    activity?.lockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
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
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(modifier = Modifier.align(Alignment.Start)) {
                navController.popBackStack()
            }
            Spacer(Modifier.height(36.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomOutlinedTextField(
                    value = amountInput,
                    onValueChange = { amountInput = it },
                    labelText = stringResource(R.string.enter_amount),
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    currencies.forEach { currency ->
                        Button(
                            onClick = { fromCurrency = currency },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (fromCurrency == currency) Green else White,
                                contentColor = if (fromCurrency == currency) White else Green
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
                                containerColor = if (toCurrency == currency) Green else White,
                                contentColor = if (toCurrency == currency) White else Green
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
}


