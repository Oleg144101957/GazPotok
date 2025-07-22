package ru.tan.alitic.rch.ui.elements

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.tan.alitic.rch.R
import ru.tan.alitic.rch.ui.theme.DefCorner
import ru.tan.alitic.rch.ui.theme.DefFont
import ru.tan.alitic.rch.ui.theme.Green
import kotlinx.coroutines.delay

@Composable
fun GreenButton(textRes: Int, modifier: Modifier = Modifier, onClick: () -> Unit) {
    var scale by remember { mutableStateOf(1f) }
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    LaunchedEffect(Unit) {
        while (true) {
            scale = 1.05f
            delay(1000)
            scale = 1f
            delay(1000)
        }
    }

    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Green, contentColor = White),
        elevation = ButtonDefaults.buttonElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth(0.8f)
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            }
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
