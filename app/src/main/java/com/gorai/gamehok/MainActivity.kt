package com.gorai.gamehok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme().copy(
                    primary = Color(0xFF00FF00),
                    primaryContainer = Color(0xFF002200)
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        NavItem(R.string.nav_home, R.drawable.ic_home),
        NavItem(R.string.nav_tournament, R.drawable.ic_tournament),
        NavItem(R.string.nav_chat, R.drawable.ic_chat),
        NavItem(R.string.nav_social, R.drawable.ic_social)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { 
                            Icon(
                                ImageVector.vectorResource(id = item.iconResId),
                                contentDescription = stringResource(id = item.labelResId)
                            )
                        },
                        label = { Text(stringResource(id = item.labelResId)) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedItem) {
                0 -> HomeScreen()
                1 -> TournamentScreen()
                2 -> ChatScreen()
                3 -> SocialScreen()
            }
        }
    }
}

private data class NavItem(
    val labelResId: Int,
    val iconResId: Int
)

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(stringResource(R.string.nav_home))
    }
}

@Composable
fun TournamentScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(stringResource(R.string.nav_tournament))
    }
}

@Composable
fun ChatScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(stringResource(R.string.nav_chat))
    }
}

@Composable
fun SocialScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(stringResource(R.string.nav_social))
    }
}
