package com.bayka.capitfin.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bayka.capitfin.ui.theme.Red

@Composable
fun RedButton(textRes: Int, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Red, contentColor = White),
        elevation = ButtonDefaults.buttonElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth(0.8f)
    ) {
        Text(
            stringResource(textRes),
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
fun WhiteButton(textRes: Int, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = White, contentColor = Red),
        elevation = ButtonDefaults.buttonElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth(0.8f)
    ) {
        Text(
            stringResource(textRes),
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}
