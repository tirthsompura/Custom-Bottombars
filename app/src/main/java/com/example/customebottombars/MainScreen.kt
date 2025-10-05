package com.example.customebottombars

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.customebottombars.bottombar.WaveBottomBar
import com.example.customebottombars.bottombar.CurvedBottomBar
import com.example.customebottombars.bottombar.CircularBottomBar
import com.example.customebottombars.bottombar.CustomBottomBar
import com.example.customebottombars.bottombar.NewModernDesignBottomBar
import com.example.customebottombars.bottombar.ModernBottomBar
import com.example.customebottombars.bottombar.ModernBottomBarWithAnimation
import com.example.customebottombars.bottombar.SquareBottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier) {
    var selectedTabSquareBottomBar by remember { mutableStateOf(0) }
    var selectedTabModernBottomBar by remember { mutableStateOf(0) }
    var selectedTabModernBottomBarWithAnimation by remember { mutableStateOf(0) }
    var selectedTabCircularBottomBar by remember { mutableStateOf(0) }
    var selectedTabNewModernDesign by remember { mutableStateOf(0) }
    var selectedTabCurvedBottomBar by remember { mutableStateOf(0) }
    var selectedTabWaveBottomBar by remember { mutableStateOf(0) }
    var selected by remember { mutableStateOf(0) }
    val items = listOf(
        Icons.Default.Home,
        Icons.Default.AccountBox,
        Icons.Default.CheckCircle,
        Icons.Default.AccountBox
    )
    val labels = listOf("Home", "Favorites", "Add", "Profile")

    Scaffold(
        topBar = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    "Example", style = TextStyle(
                        color = Color.Green.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.clickable {
                    }
                )
            }
        },
        containerColor = Color(0XFFf5f5f5).copy(alpha = 0.5f),
    ) {
        LazyColumn(
            modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            //Wave BottomBar
            item() {
                Button(title = "Wave BottomBar") {}
                Spacer(modifier = Modifier.height(50.dp))
                Box(
                    Modifier.padding(start = 20.dp, end = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    WaveBottomBar(selectedIndex = selectedTabWaveBottomBar, items = items, labels = labels, onItemSelected = {
                        selectedTabWaveBottomBar = it
                    })
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            //Modern BottomBar
            item() {
                Button(title = "Modern BottomBar") {}
                Spacer(modifier = Modifier.height(10.dp))
                Box(Modifier.padding(start = 20.dp, end = 20.dp)) {
                    ModernBottomBar(selectedIndex = selectedTabModernBottomBar,items) {
                        selectedTabModernBottomBar = it
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            //Modern BottomBar With Animation
            item() {
                Button(title = "Modern BottomBar With Animation") {}
                Spacer(modifier = Modifier.height(10.dp))
                Box(Modifier.padding(start = 20.dp, end = 20.dp)) {
                    ModernBottomBarWithAnimation(selectedIndex = selectedTabModernBottomBarWithAnimation,items) {
                        selectedTabModernBottomBarWithAnimation = it
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            //NewModernDesignBottomBar
            item() {
                Button(title = "New Modern BottomBar") {}
                Spacer(modifier = Modifier.height(30.dp))
                Box(Modifier.padding(start = 20.dp, end = 20.dp)) {
                    NewModernDesignBottomBar(selectedIndex = selectedTabNewModernDesign,items) {
                        selectedTabNewModernDesign = it
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            //Circular BottomBar
            item() {
                Button(title = "Circular BottomBar") {}
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    Modifier
                        .height(180.dp)
                        .padding(start = 20.dp, end = 20.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    CircularBottomBar(selectedIndex = selectedTabCircularBottomBar,items) {
                        selectedTabCircularBottomBar = it
                    }
                }
            }

            //Square BottomBar
            item() {
                Button(title = "Square BottomBar") {}
                Spacer(modifier = Modifier.height(10.dp))
                Box(Modifier.padding(start = 20.dp, end = 20.dp)) {
                    SquareBottomBar(selectedIndex = selectedTabSquareBottomBar,items) {
                        selectedTabSquareBottomBar = it
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            //Curved BottomBar
            item() {
                Button(title = "Curved BottomBar") {}
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    Modifier
                        .height(100.dp)
                        .padding(start = 20.dp, end = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CurvedBottomBar(selectedIndex = selectedTabCurvedBottomBar,items) {
                        selectedTabCurvedBottomBar = it
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            //Custom BottomBar
            item() {
                Button(title = "Custom BottomBar") {}
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    Modifier
                        .height(100.dp)
                        .padding(start = 20.dp, end = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CustomBottomBar(selectedIndex = selected, items = items) {
                        selected = it
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }
}

@Composable
fun Button(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(Color.Black)
            .clickable(
                onClick = {
                    onClick()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = title,
            color = Color.White
        )
    }
}