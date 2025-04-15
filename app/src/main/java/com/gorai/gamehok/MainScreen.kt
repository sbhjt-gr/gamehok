package com.gorai.gamehok

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gorai.gamehok.NavItem
import com.gorai.gamehok.HomeScreen
import com.gorai.gamehok.TournamentScreen
import com.gorai.gamehok.SocialScreen
import com.gorai.gamehok.ChatScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        NavItem(R.string.nav_home, R.drawable.ic_home),
        NavItem(R.string.nav_tournament, R.drawable.ic_tournament),
        NavItem(R.string.nav_social, R.drawable.ic_social),
        NavItem(R.string.nav_chat, R.drawable.ic_chat)
    )

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black,
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
                2 -> SocialScreen()
                3 -> ChatScreen()
            }
        }
    }
}