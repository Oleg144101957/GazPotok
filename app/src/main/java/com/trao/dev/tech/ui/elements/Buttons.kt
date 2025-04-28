package com.trao.dev.tech.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trao.dev.tech.R
import com.trao.dev.tech.ui.theme.Red

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

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    iconRes: Int = R.drawable.trao_ic_back,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier.size(40.dp)
    ) {
        Icon(
            painterResource(iconRes),
            contentDescription = iconRes.toString(),
            tint = Red,
            modifier = Modifier.size(32.dp)
        )
    }
}
