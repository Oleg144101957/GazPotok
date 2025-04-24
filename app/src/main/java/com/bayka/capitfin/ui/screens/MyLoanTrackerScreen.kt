package com.bayka.capitfin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bayka.capitfin.R
import com.bayka.capitfin.ui.elements.Background
import com.bayka.capitfin.ui.elements.IconButton
import com.bayka.capitfin.ui.theme.DarkPink
import com.bayka.capitfin.ui.theme.Red

@Composable
fun MyLoanTrackerScreen(navController: NavController, innerPadding: PaddingValues) {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White.copy(0.5f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(modifier = Modifier.align(Alignment.Start)) {
                navController.popBackStack()
            }
            Spacer(Modifier.height(36.dp))
            OutlinedTextField(
                value = loanAmount,
                onValueChange = { loanAmount = it },
                label = {
                    Text(
                        stringResource(R.string.loan_amount),
                        fontSize = 18.sp,
                        color = Red
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
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

            OutlinedTextField(
                value = interestRate,
                onValueChange = { interestRate = it },
                label = {
                    Text(
                        stringResource(R.string.interest_rate),
                        fontSize = 18.sp,
                        color = Red
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
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

            OutlinedTextField(
                value = loanTerm,
                onValueChange = { loanTerm = it },
                label = {
                    Text(
                        stringResource(R.string.loan_term_in_months),
                        fontSize = 18.sp,
                        color = Red
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
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

            OutlinedTextField(
                value = paidSoFar,
                onValueChange = { paidSoFar = it },
                label = {
                    Text(
                        stringResource(R.string.already_paid),
                        fontSize = 18.sp,
                        color = Red
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
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

            Spacer(Modifier.height(16.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .background(DarkPink, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (totalPayment != null) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            stringResource(R.string.total_amount_of_payments_2f).format(totalPayment),
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
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}
