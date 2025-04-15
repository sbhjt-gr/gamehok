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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.ui.Modifier
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import coil.compose.rememberAsyncImagePainter

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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = tournament.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "By ${tournament.organizerDetails.name}",
                                fontSize = 14.sp,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFF006400).copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = tournament.gameName,
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFF006400).copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = "Entry-${tournament.entryFees} 🪙",
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = tournament.organizerDetails.profileImagePath,
                                placeholder = painterResource(id = R.drawable.img512),
                                error = painterResource(id = R.drawable.img512)
                            ),
                            contentDescription = "Organizer Logo",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.White, CircleShape),
                            contentScale = ContentScale.Crop
                        )
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

                                Spacer(modifier = Modifier.height(24.dp))
                                
                                Text(
                                    text = "Rounds and Schedule",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                                
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    RoundItem(
                                        round = "Qualifiers (Round 1)",
                                        info = "Top 4 to next round",
                                        date = "3rd Aug, 10:00pm",
                                        isFirst = true,
                                        isLast = false
                                    )
                                    RoundItem(
                                        round = "Qualifiers (Round 1)",
                                        info = "Top 4 to next round",
                                        date = "3rd Aug, 10:00pm",
                                        isFirst = false,
                                        isLast = false
                                    )
                                    RoundItem(
                                        round = "Qualifiers (Round 1)",
                                        info = "Top 4 to next round",
                                        date = "3rd Aug, 10:00pm",
                                        isFirst = false,
                                        isLast = true
                                    )
                                }

                                Spacer(modifier = Modifier.height(24.dp))
                                
                                Text(
                                    text = "How to Join a Match",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                                
                                Column(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)

                                ) {
                                    StepText("Have your game open already and on the latest version")
                                    StepText("Once the match is configured you will receive an invite in-game to join the lobby")
                                    StepText("Join the match and wait for the game to start")
                                    StepText("When eliminated return to the match room page to be ready to join the next map in the round")
                                }

                                Spacer(modifier = Modifier.height(24.dp)) 
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(
                                            BorderStroke(1.dp, Color(0xFF888888)),
                                            shape = RoundedCornerShape(8.dp)
                                        ),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                                    elevation = CardDefaults.cardElevation(0.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(
                                                    color = Color(0xFF1C3D2A),
                                                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                                                )
                                                .padding(horizontal = 16.dp, vertical = 12.dp)
                                        ) {
                                            Text(
                                                text = "Organiser's Details and Contact",
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color.White
                                            )
                                        }

                                        Divider(
                                            color = Color(0xFF2A5A3A),
                                            thickness = 1.dp
                                        )

                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(
                                                    color = Color.Black,
                                                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                                                )
                                                .padding(16.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(bottom = 16.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Row(
                                                    modifier = Modifier.weight(1f),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.gamehok_logo_small),
                                                        contentDescription = "Organizer Logo",
                                                        modifier = Modifier
                                                            .size(32.dp)
                                                            .clip(RoundedCornerShape(4.dp)),
                                                        contentScale = ContentScale.Fit
                                                    )

                                                    Spacer(modifier = Modifier.width(12.dp))

                                                    Column {
                                                        Text(
                                                            text = "Gamehok Esports",
                                                            fontSize = 18.sp,
                                                            fontWeight = FontWeight.Bold,
                                                            color = Color.White
                                                        )
                                                        Text(
                                                            text = "This is the in house organiser of this platform you can follow our page on this platform for regular updates",
                                                            fontSize = 12.sp,
                                                            color = Color.White.copy(alpha = 0.7f),
                                                            lineHeight = 16.sp
                                                        )
                                                    }
                                                }

                                                Spacer(modifier = Modifier.width(8.dp))
                                                
                                                Button(
                                                    onClick = { /* Handle follow action */ },
                                                    modifier = Modifier.height(36.dp),
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = Color(0xFF144D2A)
                                                    ),
                                                    shape = RoundedCornerShape(6.dp),
                                                    contentPadding = PaddingValues(horizontal = 16.dp)
                                                ) {
                                                    Text(
                                                        text = "Follow",
                                                        fontSize = 14.sp,
                                                        color = Color.White
                                                    )
                                                }
                                            }

                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                                            ) {
                                                Column(modifier = Modifier.weight(1f)) {
                                                    ContactItem(
                                                        icon = Icons.Default.Phone,
                                                        text = "9890987754"
                                                    )
                                                    ContactItem(
                                                        icon = ImageVector.vectorResource(id = R.drawable.ic_whatsapp),
                                                        text = "9890987754"
                                                    )
                                                }
                                                Column(modifier = Modifier.weight(1f)) {
                                                    ContactItem(
                                                        icon = Icons.Default.Email,
                                                        text = "Support@gamehok.com"
                                                    )
                                                }
                                            }
                                        }
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
                            }
                        }
                        2 -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp)) 
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.2f),
                                    Color.Black.copy(alpha = 0.6f),
                                    Color.Black
                                ),
                                startY = 0f,
                                endY = 48f
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Divider(
                            color = Color(0xFF333333),
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 4.dp,
                                    spotColor = Color.Black.copy(alpha = 0.25f)
                                )
                        )
                        Box(
                            modifier = Modifier
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

@Composable
private fun RoundItem(
    round: String,
    info: String,
    date: String,
    isFirst: Boolean,
    isLast: Boolean,
    modifier: Modifier = Modifier
) {
    Column {
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = round,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = info,
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF4B0082),
                                        Color(0xFF800080)
                                    )
                                ),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Single Elimination",
                            fontSize = 11.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = date,
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }
        }
        
        if (!isLast) {
            Divider(
                color = Color(0xFF333333),
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun StepText(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier.padding(end = 8.dp, top = 2.dp)
        )
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ContactItem(
    icon: ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.8f),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}