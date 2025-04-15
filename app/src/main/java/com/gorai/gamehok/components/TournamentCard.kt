package com.gorai.gamehok.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.gorai.gamehok.R
import com.gorai.gamehok.data.Tournament
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TournamentCard(
    tournament: Tournament,
    modifier: Modifier = Modifier,
    onTournamentClick: (Tournament) -> Unit = {}
) {
    val cardWidth = 340.dp
    val cardColor = Color(0xFF0D3D26)

    Card(
        modifier = modifier
            .width(cardWidth)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onTournamentClick(tournament) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = tournament.thumbnailPath,
                        placeholder = painterResource(id = R.drawable.img516),
                        error = painterResource(id = R.drawable.img516)
                    ),
                    contentDescription = tournament.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Registration Open",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_social),
                            contentDescription = "Participants",
                            modifier = Modifier.size(14.dp),
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${tournament.registeredCount}/${tournament.totalCount}",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.img512),
                    contentDescription = "Organizer Logo",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-12).dp, y = 25.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White, CircleShape)
                        .padding(4.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp)
            ) {
                Text(
                    text = tournament.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "By ${tournament.organizerDetails.name}",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TagChip(text = tournament.gameName)
                    TagChip(text = if (tournament.teamSize == "1") "Solo" else tournament.teamSize)
                    TagChip(text = "Entry-${tournament.entryFees} 🪙")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "🕒",
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Starts ${formatTimestamp(tournament.tournamentStartTime)}",
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "🏆",
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Prize Pool- ${tournament.prizeCoins}",
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "🪙",
                            fontSize = 13.sp,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "›",
                        fontSize = 24.sp,
                        color = Color(0xFF2ECC71),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun TagChip(text: String) {
    Box(
        modifier = Modifier
            .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    return try {
        val date = Date(timestamp * 1000)
        val dayFormat = SimpleDateFormat("d", Locale.getDefault())
        val day = dayFormat.format(date)
        val dayInt = day.toIntOrNull() ?: 0
        val suffix = when {
            dayInt % 10 == 1 && dayInt != 11 -> "st"
            dayInt % 10 == 2 && dayInt != 12 -> "nd"
            dayInt % 10 == 3 && dayInt != 13 -> "rd"
            else -> "th"
        }
        val finalFormat = SimpleDateFormat("'$day$suffix' MMM 'at' hh:mm a", Locale.getDefault())
        finalFormat.format(date).lowercase(Locale.getDefault())
    } catch (e: Exception) {
        println("Error formatting timestamp: $e")
        "Invalid Date"
    }
}