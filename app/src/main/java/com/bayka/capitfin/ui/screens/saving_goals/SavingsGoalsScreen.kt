package com.bayka.capitfin.ui.screens.saving_goals

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bayka.capitfin.R
import com.bayka.capitfin.ui.elements.Background
import com.bayka.capitfin.ui.elements.WhiteButton

@Composable
fun SavingsGoalsScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: SavingGoalsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.savings_goal),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    categories.forEach { category ->
                        Button(
                            onClick = { selectedCategory = category },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedCategory == category) MaterialTheme.colorScheme.primary else Color.LightGray
                            )
                        ) {
                            Text(category)
                        }
                    }
                }

                Spacer(Modifier.height(50.dp))

                OutlinedTextField(
                    value = goalName,
                    onValueChange = { goalName = it },
                    label = { Text(stringResource(R.string.goal_name), color = Color.White) },
                    textStyle = TextStyle(color = Color.White),
                    colors = getCustomTextFieldColors()
                )

                OutlinedTextField(
                    value = goalAmount,
                    onValueChange = { goalAmount = it },
                    label = { Text(stringResource(R.string.total_needed), color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(color = Color.White),
                    colors = getCustomTextFieldColors()
                )

                OutlinedTextField(
                    value = addedAmount,
                    onValueChange = { addedAmount = it },
                    label = { Text(stringResource(R.string.add_now), color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(color = Color.White),
                    colors = getCustomTextFieldColors()
                )

                WhiteButton(if (editIndex != null) R.string.update_goal else R.string.save_goal) {
                    val summary = "$selectedCategory: $goalName — $addedAmount / $goalAmount"
                    viewModel.saveGoal(summary, editIndex)
                    editIndex = null
                    goalName = ""
                    goalAmount = ""
                    addedAmount = ""
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    stringResource(R.string.your_goals),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                savingsList.forEachIndexed { index, item ->
                    Text(
                        text = item,
                        color = Color.White,
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


@Composable
private fun getCustomTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    focusedLabelColor = Color.White,
    unfocusedLabelColor = Color.White,
    cursorColor = Color.White,
    focusedBorderColor = Color.White,
    unfocusedBorderColor = Color.Gray
)
