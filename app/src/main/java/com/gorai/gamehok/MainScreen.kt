package com.gorai.gamehok

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gorai.gamehok.data.Game
import com.gorai.gamehok.data.Tournament

sealed class Screen {
    object Home : Screen()
    object TournamentList : Screen()
    object Social : Screen()
    object Chat : Screen()
    data class TournamentDetails(val tournament: Tournament) : Screen()
    data class GameDetails(val game: Game) : Screen()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    val items = listOf(
        NavItem(R.string.nav_home, R.drawable.ic_home),
        NavItem(R.string.nav_tournament, R.drawable.ic_tournament),
        NavItem(R.string.nav_social, R.drawable.ic_social),
        NavItem(R.string.nav_chat, R.drawable.ic_chat)
    )

    val showBottomBar = currentScreen !is Screen.TournamentDetails && currentScreen !is Screen.GameDetails
    val showTopBar = currentScreen !is Screen.TournamentDetails && currentScreen !is Screen.GameDetails

    Scaffold(
        topBar = { 
            if (showTopBar) {
                TopBar()
            }
        },
        bottomBar = {
            if (showBottomBar) {
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
                            selected = when (currentScreen) {
                                Screen.Home -> index == 0
                                Screen.TournamentList -> index == 1
                                Screen.Social -> index == 2
                                Screen.Chat -> index == 3
                                is Screen.TournamentDetails -> false
                                is Screen.GameDetails -> false
                            },
                            onClick = {
                                currentScreen = when (index) {
                                    0 -> Screen.Home
                                    1 -> Screen.TournamentList
                                    2 -> Screen.Social
                                    3 -> Screen.Chat
                                    else -> Screen.Home
                                }
                            },
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
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = {
                    if (targetState is Screen.TournamentDetails || targetState is Screen.GameDetails) {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth }, // start from right
                            animationSpec = tween(300)
                        ) + fadeIn() with
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth }, // exit to left
                            animationSpec = tween(300)
                        ) + fadeOut()
                    } else {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> -fullWidth }, // start from left
                            animationSpec = tween(300)
                        ) + fadeIn() with
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth }, // exit to right
                            animationSpec = tween(300)
                        ) + fadeOut()
                    }
                }
            ) { screen ->
                when (screen) {
                    Screen.Home -> HomeScreen(
                        onTournamentClick = { tournament ->
                            currentScreen = Screen.TournamentDetails(tournament)
                        },
                        onGameClick = { game ->
                            currentScreen = Screen.GameDetails(game)
                        }
                    )
                    Screen.TournamentList -> TournamentScreen()
                    Screen.Social -> SocialScreen()
                    Screen.Chat -> ChatScreen()
                    is Screen.TournamentDetails -> {
                        val tournament = (screen as Screen.TournamentDetails).tournament
                        TournamentDetailsScreen(
                            tournament = tournament,
                            onBackClick = {
                                currentScreen = Screen.Home
                            }
                        )
                    }
                    is Screen.GameDetails -> {
                        val game = (screen as Screen.GameDetails).game
                        GameDetailsScreen(
                            game = game,
                            onBackClick = {
                                currentScreen = Screen.Home
                            }
                        )
                    }
                }
            }
        }
    }
}