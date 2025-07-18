package gf.na.chisle.nia.ui.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun CircularSlider(
    modifier: Modifier = Modifier,
    value: Int,
    onValueChange: (Int) -> Unit,
    minValue: Int = 1,
    maxValue: Int = 36,
    trackColor: Color = Color(0xFFEA1C0C), // полупрозрачный желтый
    progressColor: Color = Color(0xFFD54B41), // яркий желтый
    thumbColor: Color = Color(0xFFF44336),
    strokeWidth: Dp = 20.dp
) {
    val sweepAngle = 360f * (value - minValue) / (maxValue - minValue)
    val radiusPx = with(LocalDensity.current) { 100.dp.toPx() }
    var center by remember { mutableStateOf(Offset.Zero) }

    Canvas(
        modifier = modifier
            .size(220.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val touchPoint = change.position
                    val angle = calculateAngle(center, touchPoint)
                    val newValue = angleToValue(angle, minValue, maxValue)
                    onValueChange(newValue)
                }
            }
    ) {
        center = this.center

        // Рисуем весь трек полупрозрачным желтым
        drawCircle(
            color = trackColor,
            radius = radiusPx,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )

        // Рисуем прогресс ярким желтым
        drawArc(
            color = progressColor,
            startAngle = -90f,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )

        // Рисуем ползунок
        val thumbAngleRad = Math.toRadians((sweepAngle - 90).toDouble())
        val thumbX = center.x + radiusPx * cos(thumbAngleRad).toFloat()
        val thumbY = center.y + radiusPx * sin(thumbAngleRad).toFloat()
        drawCircle(
            color = thumbColor,
            radius = strokeWidth.toPx() / 2,
            center = Offset(thumbX, thumbY)
        )
    }
}


// Функция для вычисления угла в градусах между центром и точкой касания
private fun calculateAngle(center: Offset, touchPoint: Offset): Float {
    val dx = touchPoint.x - center.x
    val dy = touchPoint.y - center.y
    var angle = Math.toDegrees(atan2(dy.toDouble(), dx.toDouble())).toFloat()
    angle = (angle + 450) % 360  // Сдвигаем, чтобы 0 был сверху
    return angle
}

// Функция для преобразования угла в значение от minValue до maxValue
private fun angleToValue(angle: Float, minValue: Int, maxValue: Int): Int {
    val range = maxValue - minValue
    val value = (angle / 360f * range).roundToInt() + minValue
    return value.coerceIn(minValue, maxValue)
}
