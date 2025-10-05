package com.example.customebottombars.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.customebottombars.bottombar.CurvedBottomBar

@Composable
fun DemoScreen(modifier: Modifier) {
    var selectedTabWaveBottomBar by remember { mutableStateOf(0) }
    val items = listOf(
        Icons.Default.Home,
        Icons.Default.AccountBox,
        Icons.Default.ShoppingCart,
        Icons.Default.CheckCircle,
        Icons.Default.AccountBox,

        )
    Scaffold(
        bottomBar = {
            CurvedBottomBar(
                selectedIndex = selectedTabWaveBottomBar,
                items = items,
                onItemSelected = {
                    selectedTabWaveBottomBar = it
                },
            )
        },
        content = { padding ->
            Column(modifier = modifier.padding(padding)) {
                when (selectedTabWaveBottomBar) {
                    0 -> Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) { Text("Home") }

                    1 -> Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) { Text("Favorite") }

                    2 -> Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) { Text("Cart") }

                    3 -> Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) { Text("Add") }

                    4 -> Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) { Text("Profile") }
                }
            }
        }
    )
}