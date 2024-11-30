package com.example.eventapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.eventapp.Screen

@Composable
fun SchoolsScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Back Icon Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigateUp() },
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Title Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "School Events",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // LazyColumn for Events
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(events1) { event ->
                EventItem(
                    event = event,
                    onClick = {
                        // Navigate to EventScreen with event details
                        navController.navigate(
                            Screen.Events.createRoute(
                                event.title,
                                event.date,
                                event.location
                            )
                        )
                    }
                )
            }
        }
    }
}

// Sample events with additional data
private val events1 = listOf(
    Event2(
        icon = Icons.Filled.School,
        title = "Graduation Day",
        date = "2024-12-01",
        location = "Main Auditorium",
        backgroundColor = Color(0xFFE6F2FF)
    ),
    Event2(
        icon = Icons.Filled.Star,
        title = "Prize Giving",
        date = "2024-12-15",
        location = "Hall B",
        backgroundColor = Color(0xFFFFF3E0)
    ),
    Event2(
        icon = Icons.Filled.Sports,
        title = "Sports Day",
        date = "2025-01-10",
        location = "Sports Complex",
        backgroundColor = Color(0xFFF0F4F8)
    ),
    Event2(
        icon = Icons.Filled.EmojiEvents,
        title = "Sports Gala",
        date = "2025-01-20",
        location = "Stadium",
        backgroundColor = Color(0xFFF0E6FF)
    )
)

// Updated Event data class
data class Event2(
    val icon: ImageVector,
    val title: String,
    val date: String,
    val location: String,
    val backgroundColor: Color
)


private val events = listOf(
    Event(
        icon = Icons.Filled.School,
        title = "Graduation Day",
        backgroundColor = Color(0xFFE6F2FF)
    ),
    Event(
        icon = Icons.Filled.Star,
        title = "Prize Giving",
        backgroundColor = Color(0xFFFFF3E0)
    ),
    Event(
        icon = Icons.Filled.Sports,
        title = "Sports Day",
        backgroundColor = Color(0xFFF0F4F8)
    ),
    Event(
        icon = Icons.Filled.EmojiEvents,
        title = "Sports Gala",
        backgroundColor = Color(0xFFF0E6FF)
    )
)

@Composable
private fun EventItem(
    event: Event2,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        shadowElevation = 2.dp,
        color = event.backgroundColor
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Colored icon background
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = event.backgroundColor.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = event.icon,
                    contentDescription = event.title,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = event.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// Fixed the type of icon from String to ImageVector
data class Event(
    val icon: ImageVector, // Changed to ImageVector
    val title: String,
    val backgroundColor: Color
)
