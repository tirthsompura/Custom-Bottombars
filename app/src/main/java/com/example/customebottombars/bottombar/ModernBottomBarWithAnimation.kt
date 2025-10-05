package com.example.customebottombars.bottombar

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ModernBottomBarWithAnimation(
    selectedIndex: Int,
    items: List<ImageVector>,
    onItemSelected: (Int) -> Unit
) {

    val iconPositions = remember { mutableStateMapOf<Int, Int>() }
    val fabOffsetX = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }

    LaunchedEffect(selectedIndex, iconPositions[selectedIndex]) {
        iconPositions[selectedIndex]?.let { targetX ->
            fabOffsetX.animateTo(
                targetValue = targetX.toFloat(),
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
            )
        }

        scale.snapTo(1f)
        scale.animateTo(1.2f, tween(durationMillis = 100))
        scale.animateTo(1f, tween(durationMillis = 200))

        rotation.snapTo(0f)
        rotation.animateTo(360f, tween(durationMillis = 600))
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color.Green.copy(alpha = 0.5f),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, icon ->
                    if (index != selectedIndex) {
                        Box(
                            modifier = Modifier
                                .onGloballyPositioned {
                                    val centerX = it.positionInParent().x + it.size.width / 2
                                    iconPositions[index] = centerX.toInt()
                                }
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {
                                        onItemSelected(index)
                                    }
                                )
                        ) {
                            Icon(imageVector = icon, contentDescription = null, tint = Color.White)
                        }
                    } else {
                        Spacer(
                            modifier = Modifier
                                .size(56.dp)
                                .onGloballyPositioned {
                                    val centerX = it.positionInParent().x + it.size.width / 2
                                    iconPositions[index] = centerX.toInt()
                                }
                        )
                    }
                }
            }
        }
        iconPositions[selectedIndex]?.let {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset {
                        val x = fabOffsetX.value.roundToInt() - if (selectedIndex == 0) 100 else 60
                        val y = 10
                        IntOffset(x, y)
                    }
                    .size(56.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer {
                            scaleX = scale.value
                            scaleY = scale.value
                        }
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { onItemSelected(selectedIndex) }
                )
                Icon(
                    modifier = Modifier.graphicsLayer {
                        rotationZ = rotation.value
                    },
                    imageVector = items[selectedIndex],
                    contentDescription = null,
                    tint = Color.Green.copy(alpha = 0.6f)
                )
            }
        }
    }
}