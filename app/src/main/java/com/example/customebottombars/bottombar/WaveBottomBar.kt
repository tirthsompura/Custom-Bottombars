package com.example.customebottombars.bottombar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WaveBottomBar(
    selectedIndex: Int,
    items: List<ImageVector>,
    labels: List<String>,
    onItemSelected: (Int) -> Unit,
) {
    val iconSize = 42.dp
    val barHeight = 55.dp
    var barWidth by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .onGloballyPositioned {
                barWidth = it.size.width.toFloat()
            }
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight)
                .background(Color.Transparent)
        ) {
            val slotWidth = barWidth / items.size
            val radius = iconSize.toPx() / 2 + 12.dp.toPx()
            val dipHeight = radius * 0.6f

            val path = Path().apply {
                moveTo(0f, 0f)
                var currentX = 0f

                for (i in items.indices) {
                    val centerX = currentX + slotWidth / 2
                    cubicTo(
                        currentX + slotWidth * 0.2f, 0f,
                        centerX - radius * 0.4f, dipHeight,
                        centerX, dipHeight
                    )
                    cubicTo(
                        centerX + radius * 0.4f, dipHeight,
                        currentX + slotWidth * 0.8f, 0f,
                        currentX + slotWidth, 0f
                    )
                    currentX += slotWidth
                }

                lineTo(barWidth, size.height)
                lineTo(0f, size.height)
                close()
            }

            drawPath(path = path, color = Color.Green.copy(alpha = 0.5f))
        }

        val density = LocalDensity.current
        val slotWidthDp = with(density) { (barWidth / items.size).toDp() }

        items.forEachIndexed { index, icon ->
            val isSelected = index == selectedIndex
            val iconColor = if (isSelected) Color.White else Color.Green.copy(alpha = 0.1f)
            val iconTextColor = if (isSelected) Color.Black else Color.Black.copy(alpha = 0.6f)

            Column(
                modifier = Modifier
                    .offset(
                        x = slotWidthDp * index,
                        y = (-30).dp
                    )
                    .width(slotWidthDp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onItemSelected(index) }),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(iconSize)
                        .background(
                            if (isSelected) Color.Green.copy(alpha = 0.5f) else Color.Transparent,
                            CircleShape
                        )
                        .border(
                            width = 2.dp,
                            shape = CircleShape,
                            color = if (!isSelected) Color.Green.copy(alpha = 0.1f) else Color.Transparent
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = labels[index],
                    color = iconTextColor,
                    fontSize = 12.sp
                )
            }
        }
    }
}