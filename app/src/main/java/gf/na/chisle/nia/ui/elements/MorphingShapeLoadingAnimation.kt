package gf.na.chisle.nia.ui.elements

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

private val STANDARD_COLORS: List<Color> = listOf(
    Color(0xFF6DD5FA), // light-blue
    Color(0xFF2980B9), // blue
    Color(0xFFf7971e)  // orange
)

@Composable
fun MorphingShapeLoadingAnimation(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp,
    colors: List<Color> = STANDARD_COLORS,
    durationMillis: Int = 1800
) {
    val transition = rememberInfiniteTransition(label = "morphingShape")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "progress"
    )

    Box(modifier = modifier.size(size)) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawMorphingShape(
                progress = progress,
                size = size.toPx(),
                colors = colors
            )
        }
    }
}

private fun DrawScope.drawMorphingShape(
    progress: Float,
    size: Float,
    colors: List<Color>
) {
    // Morphing: 0..1 — circle → square, 1..2 — square → triangle, 2..3 — triangle → circle
    val phase = progress % 3f
    val t = phase % 1f

    val color = when {
        phase < 1f -> lerpColor(colors[0], colors[1], t)
        phase < 2f -> lerpColor(colors[1], colors[2], t)
        else -> lerpColor(colors[2], colors[0], t)
    }

    val path = Path()
    val center = Offset(size / 2, size / 2)
    val radius = size / 2 * 0.85f

    when {
        phase < 1f -> {
            // Circle → square
            val corner = lerp(1f, 0f, t)
            addMorphingCircleToSquare(path, center, radius, corner)
        }

        phase < 2f -> {
            // Square → triangle
            val morph = lerp(0f, 1f, t)
            addMorphingSquareToTriangle(path, center, radius, morph)
        }

        else -> {
            // Triangle → circle
            val morph = lerp(1f, 0f, t)
            addMorphingTriangleToCircle(path, center, radius, morph)
        }
    }

    drawPath(path, color)
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float =
    start + (stop - start) * fraction

private fun lerpColor(start: Color, end: Color, fraction: Float): Color = Color(
    red = lerp(start.red, end.red, fraction),
    green = lerp(start.green, end.green, fraction),
    blue = lerp(start.blue, end.blue, fraction),
    alpha = lerp(start.alpha, end.alpha, fraction)
)

private fun addMorphingCircleToSquare(path: Path, center: Offset, radius: Float, corner: Float) {
    // corner: 1f — perfect circle, 0f — square
    val c = radius * 0.5522848f * corner // coefficient for bezier circle
    val r = radius
    val left = center.x - r
    val right = center.x + r
    val top = center.y - r
    val bottom = center.y + r

    path.reset()
    path.moveTo(center.x, top)
    path.cubicTo(center.x + c, top, right, center.y - c, right, center.y)
    path.cubicTo(right, center.y + c, center.x + c, bottom, center.x, bottom)
    path.cubicTo(center.x - c, bottom, left, center.y + c, left, center.y)
    path.cubicTo(left, center.y - c, center.x - c, top, center.x, top)
    if (corner < 1f) {
        // Add "squareness"
        val k = 1f - corner
        path.addRect(
            androidx.compose.ui.geometry.Rect(
                left + r * k,
                top + r * k,
                right - r * k,
                bottom - r * k
            )
        )
    }
    path.close()
}

private fun addMorphingSquareToTriangle(path: Path, center: Offset, radius: Float, morph: Float) {
    // morph: 0f — square, 1f — triangle
    val half = radius
    val left = center.x - half
    val right = center.x + half
    val top = center.y - half
    val bottom = center.y + half

    // Square vertices
    val square = listOf(
        Offset(left, top),
        Offset(right, top),
        Offset(right, bottom),
        Offset(left, bottom)
    )
    // Triangle vertices
    val triangle = listOf(
        Offset(center.x, top),
        Offset(right, bottom),
        Offset(left, bottom),
        Offset(center.x, top) // for closing
    )
    path.reset()
    for (i in 0..3) {
        val p = lerpOffset(square[i], triangle[i % 3], morph)
        if (i == 0) path.moveTo(p.x, p.y) else path.lineTo(p.x, p.y)
    }
    path.close()
}

private fun addMorphingTriangleToCircle(path: Path, center: Offset, radius: Float, morph: Float) {
    // morph: 1f — triangle, 0f — circle
    val triangle = List(3) { i ->
        val angle = Math.toRadians((120 * i - 90).toDouble())
        Offset(
            center.x + radius * cos(angle).toFloat(),
            center.y + radius * sin(angle).toFloat()
        )
    }
    path.reset()
    val steps = 60
    for (i in 0..steps) {
        val t = i / steps.toFloat()
        val angle = Math.toRadians((360 * t - 90).toDouble())
        val circlePoint = Offset(
            center.x + radius * cos(angle).toFloat(),
            center.y + radius * sin(angle).toFloat()
        )
        // Corresponding point of the triangle
        val triIndex = (t * 3).toInt()
        val triA = triangle[triIndex % 3]
        val triB = triangle[(triIndex + 1) % 3]
        val localT = (t * 3) % 1f
        val triPoint = lerpOffset(triA, triB, localT)
        val p = lerpOffset(triPoint, circlePoint, 1f - morph)
        if (i == 0) path.moveTo(p.x, p.y) else path.lineTo(p.x, p.y)
    }
    path.close()
}

private fun lerpOffset(a: Offset, b: Offset, t: Float): Offset =
    Offset(lerp(a.x, b.x, t), lerp(a.y, b.y, t))