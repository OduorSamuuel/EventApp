package com.example.eventapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SchoolsScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Use theme background
    ) {
        // Top bar with Back Button and Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background) // Consistent background color
                .padding(vertical = 16.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigateUp() },
                tint = MaterialTheme.colorScheme.primary // Use theme primary color
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "SCES Events",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground // Use theme onBackground color
            )
        }

        // LazyColumn for event items
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(events) { event ->
                EventItem(
                    event = event,
                    onClick = {
                        // TODO: Implement navigation to event details
                    }
                )
            }
        }
    }
}

// Static data for events
private val events = listOf(
    Event(
        icon = Icons.Filled.School,
        title = "Graduation Day",
        backgroundColor = Color(0xFFE6F2FF) // Light blue for Graduation Day
    ),
    Event(
        icon = Icons.Filled.Star,
        title = "Prize Giving",
        backgroundColor = Color(0xFFFFF3E0) // Light orange for Prize Giving
    ),
    Event(
        icon = Icons.Filled.Sports,
        title = "Sports Day",
        backgroundColor = Color(0xFFF0F4F8) // Light grayish-blue for Sports Day
    ),
    Event(
        icon = Icons.Filled.EmojiEvents,
        title = "Sports Gala",
        backgroundColor = Color(0xFFF0E6FF) // Light purple for Sports Gala
    )
)

@Composable
private fun EventItem(
    event: Event,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        shadowElevation = 4.dp,
        color = event.backgroundColor // Event-specific background color
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Icon container with a subtle background
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = event.backgroundColor.copy(alpha = 0.3f), // Semi-transparent background
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = event.icon,
                    contentDescription = event.title,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary // Use theme primary color for icons
                )
            }

            Text(
                text = event.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface // Use theme onSurface color
            )
        }
    }
}

// Data class for Event
data class Event(
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val title: String,
    val backgroundColor: Color
)
