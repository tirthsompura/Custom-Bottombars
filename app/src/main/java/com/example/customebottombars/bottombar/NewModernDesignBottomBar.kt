package com.example.customebottombars.bottombar

import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NewModernDesignBottomBar(
    selectedIndex: Int,
    items: List<ImageVector>,
    onItemSelected: (Int) -> Unit
) {

    Box(
        Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Green.copy(alpha = 0.5f), shape = RoundedCornerShape(24.dp))
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, icon ->
                val isSelected = index == selectedIndex

                val iconOffsetY by animateDpAsState(
                    targetValue = if (isSelected) (-20).dp else 0.dp,
                    label = "iconYOffset"
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .offset(y = iconOffsetY)
                        .size(if (isSelected) 56.dp else 48.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.White else Color.Transparent)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onItemSelected(index) }),
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (isSelected) Color.Green.copy(alpha = 0.5f) else Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}