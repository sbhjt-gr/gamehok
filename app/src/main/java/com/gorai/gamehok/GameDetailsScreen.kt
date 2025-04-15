package com.gorai.gamehok

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.BackHandler
import com.gorai.gamehok.data.Game
import com.gorai.gamehok.data.Tournament
import com.gorai.gamehok.data.OrganizerDetails
import com.gorai.gamehok.components.TournamentCard
import com.gorai.gamehok.R
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.foundation.layout.statusBarsPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsScreen(
    game: Game,
    onBackClick: () -> Unit
) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val tabs = listOf("Overview", "Tournaments", "Leaderboard")

    BackHandler {
        onBackClick()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { 
                        Text(
                            game.gameName, 
                            color = Color.White
                        ) 
                    },
                    navigationIcon = {
                        Box(
                            modifier = Modifier
                                .padding(13.dp)
                                .background(Color(0x40808080), CircleShape)
                        ) {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black.copy(alpha = 0.8f)
                    )
                )
            },
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    game.imagePath.toIntOrNull()?.let { imageRes ->
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = game.gameName,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                    ) {
                        Text(
                            text = game.gameName,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Game ID: ${game.id}",
                            fontSize = 14.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF006400).copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "Action",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    TabRow(
                        selectedTabIndex = selectedTabIndex.value,
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value]),
                                height = 2.dp,
                                color = Color.White
                            )
                        }
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex.value == index,
                                onClick = { selectedTabIndex.value = index },
                                text = {
                                    Text(
                                        text = title,
                                        color = if (selectedTabIndex.value == index) Color.White else Color.Gray
                                    )
                                }
                            )
                        }
                    }

                    when (selectedTabIndex.value) {
                        0 -> {
                            val leaderboard = remember { 
                                listOf(
                                    LeaderboardEntry("Player1", 1500, R.drawable.img501),
                                    LeaderboardEntry("LegendGamer", 1450, R.drawable.img505),
                                    LeaderboardEntry("ProSniper", 1300, R.drawable.img24),
                                    LeaderboardEntry("NoobMaster", 1200, R.drawable.img512)
                                )
                             }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "Game Details",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                                DetailItem(
                                    icon = Icons.Filled.Info,
                                    label = "GENRE",
                                    value = "Action / Shooter"
                                )
                                DetailItem(
                                    icon = Icons.Filled.List,
                                    label = "PLATFORM",
                                    value = "Mobile"
                                )
                                DetailItem(
                                    icon = Icons.Filled.DateRange,
                                    label = "RELEASE DATE",
                                    value = "Jan 1, 2023"
                                )
                                DetailItem(
                                    icon = Icons.Filled.Person,
                                    label = "DEVELOPER",
                                    value = "Game Studio X"
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                Text(
                                    text = "Description",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                                Text(
                                    text = "This is a placeholder description for ${game.gameName}. Compete in thrilling matches and climb the leaderboards!",
                                    fontSize = 14.sp,
                                    color = Color.White.copy(alpha = 0.8f),
                                    lineHeight = 20.sp
                                )
                                
                                Spacer(modifier = Modifier.height(24.dp))

                                Text(
                                    text = "Leaderboard",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                     modifier = Modifier.padding(bottom = 16.dp)
                                )
                                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.heightIn(max= 400.dp) 
                                ) {
                                    items(leaderboard.size) { index ->
                                        val entry = leaderboard[index]
                                        LeaderboardItem(
                                            rank = index + 1,
                                            name = entry.name,
                                            score = entry.score,
                                            imageRes = entry.imageRes
                                        )
                                    }
                                }
                            }
                        }
                        1 -> {
                            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                                Text("Tournaments coming soon!", color = Color.Gray)
                            }
                        }
                        2 -> {
                            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                                Text("Leaderboard coming soon!", color = Color.Gray)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun DetailItem(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF1A1A1A), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF00FF00),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color(0xFF999999)
            )
            Text(
                text = value,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

data class LeaderboardEntry(val name: String, val score: Int, val imageRes: Int)

@Composable
private fun LeaderboardItem(
    rank: Int,
    name: String,
    score: Int,
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "#$rank",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.width(40.dp)
        )
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = name,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = score.toString(),
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
} 