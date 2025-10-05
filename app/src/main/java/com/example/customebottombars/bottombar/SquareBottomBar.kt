package com.example.customebottombars.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SquareBottomBar(selectedIndex: Int, items: List<ImageVector>, onItemSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color.Green.copy(alpha = 0.5f))
            .padding(
                start = if (selectedIndex == 3) 15.dp else 5.dp,
                end = if (selectedIndex == 0) 15.dp else 5.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, icon ->
            val isSelected = selectedIndex == index

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (index != 0 && isSelected) {
                    Column {
                        Box(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .width(20.dp)
                                .height(16.dp)
                                .clip(
                                    shape = RoundedCornerShape(
                                        bottomEnd = 5.dp,
                                        bottomStart = 5.dp
                                    )
                                )
                                .background(Color.White),
                            contentAlignment = Alignment.TopEnd
                        ) {
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Box(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .width(20.dp)
                                .height(16.dp)
                                .clip(shape = RoundedCornerShape(topEnd = 5.dp, topStart = 5.dp))
                                .background(Color.White)
                        ) {}

                    }
                }

                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(height = 48.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = if (index == 0 && isSelected) 12.dp else 0.dp,
                                bottomStart = if (index == 0 && isSelected) 12.dp else 0.dp,
                                topEnd = if (index == 3 && isSelected) 12.dp else 0.dp,
                                bottomEnd = if (index == 3 && isSelected) 12.dp else 0.dp,
                            )
                        )
                        .background(if (isSelected) Color.White else Color.Transparent)
                        .clickable { onItemSelected(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (isSelected) Color.Green.copy(alpha = 0.5f) else Color.White
                    )
                }

                if (index != 3 && isSelected) {
                    Column {
                        Box(
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .width(20.dp)
                                .height(16.dp)
                                .clip(
                                    shape = RoundedCornerShape(
                                        bottomEnd = 5.dp,
                                        bottomStart = 5.dp
                                    )
                                )
                                .background(Color.White)
                        ) {}
                        Spacer(modifier = Modifier.height(30.dp))
                        Box(
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .width(20.dp)
                                .height(16.dp)
                                .clip(shape = RoundedCornerShape(topEnd = 5.dp, topStart = 5.dp))
                                .background(Color.White)
                        ) {}
                    }
                }
            }
        }
    }
}
