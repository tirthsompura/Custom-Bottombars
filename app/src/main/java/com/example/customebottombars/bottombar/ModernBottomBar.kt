package com.example.customebottombars.bottombar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ModernBottomBar(
    selectedIndex: Int,
    items: List<ImageVector>,
    onItemSelected: (Int) -> Unit
) {
    val rotation = remember { Animatable(0f) }
    val bounceOffset = remember { Animatable(0f) }

    LaunchedEffect(selectedIndex) {
        bounceOffset.snapTo(0f)
        bounceOffset.animateTo(
            targetValue = -16f,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        )
        bounceOffset.animateTo(
            targetValue = 0f,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        )

        rotation.snapTo(0f)
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
        )
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color.Green.copy(alpha = 0.5f),
            modifier = Modifier
                .padding(start = 60.dp)
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, icon ->
                    if (index != selectedIndex) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    onItemSelected(index)
                                }
                            )
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = (-10).dp, y = bounceOffset.value.dp)
                .graphicsLayer {
                    rotationZ = rotation.value
                }
                .size(56.dp)
                .clip(CircleShape)
                .background(Color.Green.copy(alpha = 0.5f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onItemSelected(selectedIndex) }),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = items[selectedIndex],
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}