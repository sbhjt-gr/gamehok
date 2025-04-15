package com.gorai.gamehok

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gorai.gamehok.data.Tournament
import androidx.activity.compose.BackHandler
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.ui.Modifier
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.graphics.Brush

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentDetailsScreen(
    tournament: Tournament,
    onBackClick: () -> Unit
) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val tabs = listOf("Overview", "Players", "Rules")

    BackHandler {
        onBackClick()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
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
                    actions = {
                        Box(
                            modifier = Modifier
                                .padding(13.dp)
                                .background(Color.Black.copy(alpha = 0.4f), CircleShape)
                        ) {
                            IconButton(
                                onClick = { /* Handle forward */ }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Forward",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img516),
                        contentDescription = tournament.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        contentScale = ContentScale.Crop
                    )

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
                                        color = Color.White
                                    )
                                }
                            )
                        }
                    }

                    when (selectedTabIndex.value) {
                        0 -> { 
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                

                                Text(
                                    text = "Details",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )

                                DetailItem(
                                    icon = Icons.Filled.Person,
                                    label = "TEAM SIZE",
                                    value = "Solo"
                                )

                                DetailItem(
                                    icon = Icons.Filled.List,
                                    label = "FORMAT",
                                    value = "Single Elimination"
                                )

                                DetailItem(
                                    icon = Icons.Filled.DateRange,
                                    label = "TOURNAMENT STARTS",
                                    value = "Tue 24th Jan 2024, 01:00 PM"
                                )

                                DetailItem(
                                    icon = Icons.Default.Warning,
                                    label = "CHECK-IN",
                                    value = "10 mins before the match starts"
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                Text(
                                    text = "Registration Status",
                                    fontSize = 14.sp,
                                    color = Color.White.copy(alpha = 0.7f)
                                )
                                Text(
                                    text = "${tournament.registeredCount}/${tournament.totalCount}",
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )

                                Spacer(modifier = Modifier.height(24.dp))
                                
                                
                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    PrizeItem("Total Tournament Prize", "2000", isHeader = true)
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(2.dp)
                                    ) {
                                        PrizeItem("1st Prize", "1000")
                                        PrizeItem("2nd Prize", "500")
                                        PrizeItem("3rd Prize", "200")
                                        PrizeItem("4th Prize", "100")
                                        PrizeItem("5th Prize", "100", isLastItem = true)
                                    }
                                }
                            }
                        }
                        1 -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                // Players tab content will be added later
                            }
                        }
                        2 -> { // Rules Tab
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                // Rules tab content will be added later
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color.Black)
                        .padding(14.dp)
                ) {
                    Button(
                        onClick = { /* Handle registration */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF23D600).copy(alpha = 0.5f)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "JOIN TOURNAMENT",
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
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

@Composable
private fun PrizeItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    isHeader: Boolean = false,
    isLastItem: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = if (isHeader) {
                        listOf(
                            Color(0xFF234223),
                            Color(0xFF1F3B1F)
                        )
                    } else {
                        listOf(
                            Color(0xFF1A2E1A),
                            Color(0xFF162816)
                        )
                    },
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY
                ),
                shape = when {
                    isHeader -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                    isLastItem -> RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 12.dp, bottomEnd = 12.dp)
                    else -> RoundedCornerShape(0.dp)
                }
            )
            .padding(horizontal = 16.dp, vertical = if (isHeader) 16.dp else 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isHeader) {
            Icon(
                painter = painterResource(id = R.drawable.ic_trophy),
                contentDescription = null,
                tint = Color(0xFF23D600),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        
        Text(
            text = label,
            fontSize = if (isHeader) 16.sp else 14.sp,
            color = Color.White,
            fontWeight = if (isHeader) FontWeight.SemiBold else FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "$value 🪙",
            fontSize = if (isHeader) 18.sp else 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}