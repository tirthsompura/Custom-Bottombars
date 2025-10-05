package com.example.customebottombars.bottombar

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircularBottomBar(
    selectedIndex: Int,
    items: List<ImageVector>,
    onItemSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val transition = updateTransition(targetState = expanded, label = "menu_transition")

    val rotation by transition.animateFloat(
        label = "fab_rotation",
        transitionSpec = { tween(durationMillis = 400, easing = FastOutSlowInEasing) }
    ) { isExpanded -> if (isExpanded) 45f else 0f }

    val radius = 100f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        items.forEachIndexed { index, icon ->
            val angleDeg = 180f / (items.size - 1) * index
            val angleRad = Math.toRadians(angleDeg.toDouble())

            val x = radius * cos(angleRad).toFloat()
            val y = radius * sin(angleRad).toFloat()

            val offsetX by transition.animateDp(
                label = "x_offset_$index",
                transitionSpec = { spring(dampingRatio = Spring.DampingRatioMediumBouncy) }
            ) {
                if (it) x.dp else 0.dp
            }

            val offsetY by transition.animateDp(
                label = "y_offset_$index",
                transitionSpec = { spring(dampingRatio = Spring.DampingRatioMediumBouncy) }
            ) {
                if (it) (-y).dp else 0.dp
            }

            if (expanded || offsetX != 0.dp || offsetY != 0.dp) {
                Box(
                    modifier = Modifier
                        .offset(x = offsetX, y = offsetY)
                        .size(50.dp)
                        .background(Color.Green.copy(alpha = 0.5f), CircleShape)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                onItemSelected(index)
                                expanded = false
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (selectedIndex == index) Color.White else Color.White.copy(alpha = 0.5f)
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { expanded = !expanded },
            containerColor = if (expanded) Color.White else Color.Green.copy(alpha = 0.5f),
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            modifier = Modifier.rotate(rotation)
        ) {
            val icon = if (expanded) Icons.Default.Close else items[selectedIndex]
            Icon(
                imageVector = icon,
                contentDescription = "FAB Icon",
                tint = if (expanded) Color.Green.copy(alpha = 0.5f) else Color.White
            )
        }
    }
}