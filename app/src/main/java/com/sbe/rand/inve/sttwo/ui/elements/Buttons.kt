package com.sbe.rand.inve.sttwo.ui.elements

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbe.rand.inve.sttwo.R
import com.sbe.rand.inve.sttwo.ui.theme.DefCorner
import com.sbe.rand.inve.sttwo.ui.theme.DefFont
import com.sbe.rand.inve.sttwo.ui.theme.Green

@Composable
fun GreenButton(textRes: Int, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Green, contentColor = White),
        elevation = ButtonDefaults.buttonElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth(0.8f)
    ) {
        Text(
            stringResource(textRes),
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun TextButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = Green,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
            .background(color, DefCorner)
            .padding(vertical = 8.dp),
        enabled = enabled
    ) {
        Text(
            text,
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = DefFont
        )
    }
}

@Composable
fun WhiteButton(textRes: Int, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = White, contentColor = Green),
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
    iconRes: Int = R.drawable.sberrasschet_ic_back,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier.size(40.dp)
    ) {
        Icon(
            painterResource(iconRes),
            contentDescription = iconRes.toString(),
            tint = White,
            modifier = Modifier.size(32.dp)
        )
    }
}
