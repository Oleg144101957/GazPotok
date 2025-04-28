package com.trao.dev.tech.ui.screens.saving_goals

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.trao.dev.R
import com.trao.dev.tech.ui.elements.Background
import com.trao.dev.tech.ui.elements.IconButton
import com.trao.dev.tech.ui.elements.WhiteButton
import com.trao.dev.tech.ui.theme.Red

@Composable
fun SavingsGoalsScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: SavingGoalsViewModel = hiltViewModel()
) {
    val categories = listOf(
        stringResource(R.string.category_travel),
        stringResource(R.string.category_education),
        stringResource(R.string.category_gadgets),
        stringResource(R.string.category_other)
    )

    var selectedCategory by remember { mutableStateOf(categories.first()) }
    var goalName by remember { mutableStateOf("") }
    var goalAmount by remember { mutableStateOf("") }
    var addedAmount by remember { mutableStateOf("") }
    var editIndex by remember { mutableStateOf<Int?>(null) }

    val savingsList = viewModel.savingsList

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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                categories.forEach { category ->
                    Button(
                        onClick = { selectedCategory = category },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedCategory == category) Red else White,
                            contentColor = if (selectedCategory == category) White else Red
                        ),
                        elevation = ButtonDefaults.buttonElevation(8.dp),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(category)
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = goalName,
                onValueChange = { goalName = it },
                label = {
                    Text(
                        stringResource(R.string.goal_name), fontSize = 18.sp,
                        color = Red
                    )
                },
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
            OutlinedTextField(
                value = goalAmount,
                onValueChange = { goalAmount = it },
                label = {
                    Text(
                        stringResource(R.string.total_needed), fontSize = 18.sp,
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
            OutlinedTextField(
                value = addedAmount,
                onValueChange = { addedAmount = it },
                label = {
                    Text(
                        stringResource(R.string.add_now), fontSize = 18.sp,
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
            WhiteButton(if (editIndex != null) R.string.update_goal else R.string.save_goal) {
                val summary = "$selectedCategory: $goalName — $addedAmount / $goalAmount"
                viewModel.saveGoal(summary, editIndex)
                editIndex = null
                goalName = ""
                goalAmount = ""
                addedAmount = ""
            }
            Spacer(Modifier.height(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    stringResource(R.string.your_goals),
                    style = TextStyle(
                        color = White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
                savingsList.forEachIndexed { index, item ->
                    Text(
                        text = item,
                        style = TextStyle(
                            color = White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.clickable {
                            val parts = item.split(": ", " — ", " / ")
                            if (parts.size == 4) {
                                selectedCategory = parts[0]
                                goalName = parts[1]
                                addedAmount = parts[2]
                                goalAmount = parts[3]
                                editIndex = index
                            }
                        }
                    )
                }
            }
        }
    }
}
