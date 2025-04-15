package com.gorai.gamehok

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.gorai.gamehok.ui.theme.*
import androidx.compose.ui.geometry.Offset
import com.gorai.gamehok.components.GameCard
import com.gorai.gamehok.viewmodel.GamesUiState
import com.gorai.gamehok.viewmodel.HomeViewModel
import com.gorai.gamehok.components.TournamentCard
import com.gorai.gamehok.viewmodel.TournamentsUiState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.gorai.gamehok.R
import com.gorai.gamehok.data.Tournament
import kotlinx.coroutines.delay
import com.gorai.gamehok.data.Game

private val GoldenStart = Color(0xFFFFD700)
private val GoldenEnd = Color(0xFFB8860B)

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onTournamentClick: (Tournament) -> Unit = {},
    onGameClick: (Game) -> Unit = {}
) {
    val gamesUiState by viewModel.gamesUiState.collectAsState()
    val tournamentsUiState by viewModel.tournamentsUiState.collectAsState()
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val pagerState = rememberPagerState()
            
            LaunchedEffect(pagerState) {
                while(true) {
                    delay(2000)
                    val nextPage = (pagerState.currentPage + 1) % 3
                    pagerState.animateScrollToPage(nextPage)
                }
            }
            
            HorizontalPager(
                count = 3,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    when (page) {
                        0 -> PremiumCard(
                            title = "Gamehok",
                            description = "Upgrade to premium membership and get 100 🎟️ and many other premium features."
                        )
                        1 -> PremiumCard(
                            title = "Pro Gaming",
                            description = "Access exclusive tournaments and compete with pro gamers worldwide."
                        )
                        2 -> PremiumCard(
                            title = "Elite Pass",
                            description = "Get exclusive in-game items and early access to new features."
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.padding(16.dp),
                activeColor = Color.White,
                inactiveColor = Color.White.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Play Tournament by Games",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "View All",
                        fontSize = 15.sp,
                        color = Color(0xFF2ECC71), // Vibrant Green color
                        modifier = Modifier.clickable { /* TODO: Handle View All click */ }
                    )
                }
                
                when (gamesUiState) {
                    is GamesUiState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                    is GamesUiState.Error -> {
                        Text(
                            text = (gamesUiState as GamesUiState.Error).message,
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    is GamesUiState.Success -> {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items((gamesUiState as GamesUiState.Success).games) { game -> 
                                GameCard(
                                    imageRes = game.imagePath.toIntOrNull() ?: R.drawable.img501,
                                    name = game.gameName,
                                    modifier = Modifier.width(120.dp),
                                    onClick = { onGameClick(game) }
                                )
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))

                Divider(
                    color = Color.Gray, 
                    thickness = 1.dp, 
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Compete in Battles",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "View All",
                        fontSize = 15.sp,
                        color = Color(0xFF2ECC71), // Lighter Gray color
                        modifier = Modifier.clickable { /* TODO: Handle View All click */ }
                    )
                }
                
                when (tournamentsUiState) {
                    is TournamentsUiState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                    is TournamentsUiState.Error -> {
                        Text(
                            text = (tournamentsUiState as TournamentsUiState.Error).message,
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    is TournamentsUiState.Success -> {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(horizontal = 4.dp)
                        ) {
                            items((tournamentsUiState as TournamentsUiState.Success).tournaments) { tournament -> 
                                TournamentCard(
                                    tournament = tournament,
                                    onTournamentClick = onTournamentClick
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "People to follow",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "View More",
                        fontSize = 15.sp,
                        color = Color(0xFF2ECC71), // Lighter Gray color
                        modifier = Modifier.clickable { /* TODO */ }
                    )
                }

                PersonToFollow(name = "Legend Gamer", imageRes = R.drawable.img505)
                PersonToFollow(name = "Legend Gamer", imageRes = R.drawable.img24)
                PersonToFollow(name = "Legend Gamer", imageRes = R.drawable.img501)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun PersonToFollow(
    name: String,
    imageRes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Profile picture of $name",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Text(
                text = name,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Button(
            onClick = { /* Handle */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF044D2C)
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 6.dp)
        ) {
            Text(
                text = "Follow",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun PremiumCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.95f) 
            .padding(horizontal = 8.dp, vertical = 8.dp) 
            .clip(RoundedCornerShape(14.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colorStops = arrayOf(
                            0.0f to CardBackgroundStart,
                            0.5f to CardBackgroundMiddle,
                            1.0f to CardBackgroundEnd
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    )
                )
                .padding(20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            fontSize = 24.sp, 
                            fontWeight = FontWeight.Bold,
                            color = PremiumTextColor
                        )
                        Box(
                            modifier = Modifier
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(GoldenStart, GoldenEnd),
                                        start = Offset(0f, 0f),
                                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Premium",
                                fontSize = 14.sp, 
                                color = Color.White
                            )
                        }
                    }
                    
                    Button(
                        onClick = { /* Handle */ },
                        colors = ButtonDefaults.buttonColors(containerColor = PremiumGetNowButton),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Get Now",
                            fontSize = 18.sp, 
                            color = Color.White
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(20.dp)) 
                
                Text(
                    text = description,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
                
                Spacer(modifier = Modifier.height(10.dp)) 
                
                Text(
                    text = "View All Features ›",
                    color = PremiumFeatureLink,
                    fontSize = 14.sp, 
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
