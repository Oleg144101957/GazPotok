package gf.na.chisle.nia.ui.screens.add_info

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import gf.na.chisle.nia.R
import gf.na.chisle.nia.domain.Expense
import gf.na.chisle.nia.ui.elements.Background
import gf.na.chisle.nia.ui.elements.CategoryDropdown
import gf.na.chisle.nia.ui.elements.CustomOutlinedTextField
import gf.na.chisle.nia.ui.elements.GreenButton
import java.io.File
import java.io.FileOutputStream

@Composable
fun AddInfoScreen(navController: NavController, viewModel: ExpensesViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val photoUriState = remember { mutableStateOf<Uri?>(null) }
    val amount = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        bitmap?.let {
            val savedUri = saveBitmapToCache(context, it)
            photoUriState.value = savedUri
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(),
        contentAlignment = Alignment.Center
    ) {
        Background()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            photoUriState.value?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            CustomOutlinedTextField(
                value = amount.value,
                onValueChange = { amount.value = it },
                labelText = "Сумма",
                keyboardType = KeyboardType.Number
            )
            CustomOutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                labelText = "На что потрачено"
            )

            CategoryDropdown(
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

            GreenButton(
                textRes = R.string.add_photo, // Добавь в strings.xml <string name="add_photo">Добавить фото чека</string>
                onClick = { cameraLauncher.launch() }
            )

            GreenButton(
                textRes = R.string.save_expense, // Добавь в strings.xml <string name="save_expense">Сохранить трату</string>
                onClick = {
                    if (amount.value.isBlank() || description.value.isBlank() || selectedCategory.isBlank() || photoUriState.value == null) {
                        Toast.makeText(
                            context,
                            "Заполните все поля и сделайте фото чека",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.saveExpense(
                            Expense(
                                amount = amount.value,
                                description = description.value,
                                category = selectedCategory,
                                photoUri = photoUriState.value?.toString() ?: "",
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}


fun saveBitmapToCache(context: Context, bitmap: Bitmap): Uri {
    val file = File(context.cacheDir, "photo_${System.currentTimeMillis()}.jpg")
    val outputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    outputStream.flush()
    outputStream.close()
    return file.toUri()
}
