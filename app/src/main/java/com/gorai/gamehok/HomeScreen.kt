package com.gorai.gamehok

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.gorai.gamehok.ui.theme.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.gorai.gamehok.components.GameCard

private val GoldenStart = Color(0xFFFFD700)
private val GoldenEnd = Color(0xFFB8860B)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val pagerState = rememberPagerState()
            
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
                Text(
                    text = "Play Tournament by Games",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    GameCard(
                        imageRes = R.drawable.img516,
                        name = "PUBG",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    GameCard(
                        imageRes = R.drawable.img28,
                        name = "Call of Duty",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    GameCard(
                        imageRes = R.drawable.img528,
                        name = "Counter Strike",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun PremiumCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.95f) 
            .padding(horizontal = 8.dp, vertical = 8.dp) 
            .clip(RoundedCornerShape(24.dp)),
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
                        shape = RoundedCornerShape(24.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Get Now",
                            fontSize = 14.sp, 
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
