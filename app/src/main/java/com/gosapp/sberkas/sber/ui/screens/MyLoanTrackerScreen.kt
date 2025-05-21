package com.gosapp.sberkas.sber.ui.screens

import android.app.Activity
import android.content.pm.ActivityInfo
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
import androidx.navigation.NavController
import com.gosapp.sberkas.sber.R
import com.gosapp.sberkas.sber.ui.elements.Background
import com.gosapp.sberkas.sber.ui.elements.CustomOutlinedTextField
import com.gosapp.sberkas.sber.ui.elements.IconButton
import com.gosapp.sberkas.sber.util.lockOrientation

@Composable
fun MyLoanTrackerScreen(navController: NavController, innerPadding: PaddingValues) {

    val context = LocalContext.current
    val activity = context as? Activity
    activity?.lockOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    var loanAmount by remember { mutableStateOf("") }
    var interestRate by remember { mutableStateOf("") }
    var loanTerm by remember { mutableStateOf("") }
    var paidSoFar by remember { mutableStateOf("") }

    val totalPayment = calculateLoanTotalPayment(
        loanAmount.toDoubleOrNull(),
        interestRate.toDoubleOrNull(),
        loanTerm.toIntOrNull()
    )

    val remainingBalance = totalPayment?.let {
        val paid = paidSoFar.toDoubleOrNull() ?: 0.0
        (it - paid).coerceAtLeast(0.0)
    }

    val overpayment = totalPayment?.let {
        val initial = loanAmount.toDoubleOrNull() ?: 0.0
        it - initial
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Background()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(modifier = Modifier.align(Alignment.Start)) {
                navController.popBackStack()
            }
            Spacer(Modifier.height(36.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CustomOutlinedTextField(
                    value = loanAmount,
                    onValueChange = { loanAmount = it },
                    labelText = stringResource(R.string.loan_amount),
                    keyboardType = KeyboardType.Number
                )
                Spacer(Modifier.height(16.dp))
                CustomOutlinedTextField(
                    value = interestRate,
                    onValueChange = { interestRate = it },
                    labelText = stringResource(R.string.interest_rate),
                    keyboardType = KeyboardType.Number
                )
                Spacer(Modifier.height(16.dp))
                CustomOutlinedTextField(
                    value = loanTerm,
                    onValueChange = { loanTerm = it },
                    labelText = stringResource(R.string.loan_term_in_months),
                    keyboardType = KeyboardType.Number
                )
                Spacer(Modifier.height(16.dp))
                CustomOutlinedTextField(
                    value = paidSoFar,
                    onValueChange = { paidSoFar = it },
                    labelText = stringResource(R.string.already_paid),
                    keyboardType = KeyboardType.Number
                )
                Spacer(Modifier.height(16.dp))

                if (totalPayment != null) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            stringResource(R.string.total_amount_of_payments_2f).format(
                                totalPayment
                            ),
                            style = TextStyle(
                                color = White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            stringResource(R.string.overpayment_2f).format(overpayment),
                            style = TextStyle(
                                color = White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            stringResource(R.string.loan_balance_2f).format(remainingBalance),
                            style = TextStyle(
                                color = White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                } else {
                    Text(
                        stringResource(R.string.please_enter_correct_data),
                        style = TextStyle(
                            color = White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}
