package com.example.customebottombars.bottombar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomBar(
    selectedIndex: Int,
    items: List<ImageVector>,
    onItemSelected: (Int) -> Unit
) {
    val iconSize = 32.dp
    val barHeight = 70.dp

    var barWidth by remember { mutableStateOf(0f) }

    val animatedSelectedX by animateFloatAsState(
        targetValue = selectedIndex.toFloat(),
        label = "curveX"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                barWidth = it.size.width.toFloat()
            }
            .clip(shape = RoundedCornerShape(12.dp))
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight)
        ) {
            val totalItems = items.size
            val slotWidth = barWidth / totalItems
            val centerX = slotWidth * animatedSelectedX + slotWidth / 2.5.toInt()
            val notchRadius = iconSize.toPx() / 2.5.toInt()

            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(centerX - notchRadius * 2f, 0f)

                cubicTo(
                    centerX - notchRadius * 1.2f, 0f,
                    centerX - notchRadius, notchRadius * 1.5f,
                    centerX, notchRadius * 1.5f
                )

                cubicTo(
                    centerX + notchRadius, notchRadius * 1.5f,
                    centerX + notchRadius * 1.2f, 0f,
                    centerX + notchRadius * 2f, 0f
                )

                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            drawPath(path, color = Color.Green.copy(alpha = 0.5f))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            items.forEachIndexed { index, icon ->
                val targetColor =
                    if (index != selectedIndex) Color.White.copy(alpha = 0.5f) else Color.White
                val animatedColor by animateColorAsState(
                    targetValue = targetColor,
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = FastOutSlowInEasing
                    ), label = ""
                )
                Box(
                    modifier = Modifier
                        .offset(y = (-10).dp)
                        .clickable { onItemSelected(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = animatedColor,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }


        val slotWidthPx = if (barWidth != 0f) barWidth / items.size else 0f
        val iconOffsetX =
            slotWidthPx * selectedIndex + slotWidthPx / 2 - with(LocalDensity.current) { iconSize.toPx() / 2 }
        val offsetX by animateDpAsState(
            targetValue = with(LocalDensity.current) { iconOffsetX.toDp() },
            label = "iconOffset"
        )

        Box(
            modifier = Modifier
                .offset(x = offsetX, y = (-20).dp)
                .size(iconSize)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onItemSelected(selectedIndex) }
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .offset(y = (12).dp)
                    .size(12.dp)
                    .background(Color.Green.copy(alpha = 0.5f), CircleShape)
            )
        }
    }
}