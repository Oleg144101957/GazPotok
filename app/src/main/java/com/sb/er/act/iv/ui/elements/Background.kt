package com.sb.er.act.iv.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.sb.er.act.iv.R

@Composable
fun Background(drawable: Int = R.drawable.bg) {
    Image(
        painter = painterResource(id = drawable),
        contentDescription = drawable.toString(),
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}