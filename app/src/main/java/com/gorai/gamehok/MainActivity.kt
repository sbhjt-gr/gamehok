package com.gorai.gamehok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import com.gorai.gamehok.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme().copy(
                    primary = Color(0xFF00FF00),
                    primaryContainer = Color(0xFF002200),
                    background = Color.Black,
                    surface = Color(0xFF101010), 
                    onBackground = Color(0xFF00FF00),
                    onSurface = Color(0xFF00FF00)
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    androidx.compose.foundation.Canvas(modifier = Modifier.matchParentSize()) {
                        drawRect(
                            brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0x8000FF00)
                                ),
                                startY = 0f,
                                endY = size.height
                            ),
                            size = size
                        )
                    }
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Transparent
                    ) {
                        MainScreen()
                    }
                }
            }
        }
    }
}

