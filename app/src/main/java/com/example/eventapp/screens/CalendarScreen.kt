package com.example.eventapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Event data class
data class Event1(
    val date: LocalDate,
    val title: String,
    val backgroundColor: Color
)

@SuppressLint("NewApi")
@Composable
fun CalendarScreen(
    navController: NavHostController,
    initialSelectedDate: LocalDate = LocalDate.now()
) {
    var selectedDate by remember { mutableStateOf(initialSelectedDate) }
    var eventsForSelectedDate by remember { mutableStateOf<List<Event1>>(emptyList()) }

    // Sample static events
    val events = listOf(
        Event1(LocalDate.parse("2024-11-28"), "Graduation Day", Color(0xFFE6F2FF)),
        Event1(LocalDate.parse("2024-11-29"), "Prize Giving", Color(0xFFFFF3E0)),
        Event1(LocalDate.parse("2024-11-30"), "Sports Day", Color(0xFFF0F4F8)),
        Event1(LocalDate.parse("2024-12-01"), "Sports Gala", Color(0xFFF0E6FF))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Calendar Header
        Text(
            text = "Select a Date",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display current month and year
        val currentMonth = selectedDate.month
        val currentYear = selectedDate.year
        Text(
            text = "${currentMonth.name} $currentYear",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Calendar Grid
        CalendarGrid(
            selectedDate = selectedDate,
            onDateClick = { date ->
                selectedDate = date
                eventsForSelectedDate = events.filter { it.date == selectedDate }
            }
        )

        // Display selected date
        Text(
            text = "Selected Date: ${selectedDate.format(DateTimeFormatter.ISO_DATE)}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Events section
        Text(
            text = "Events for ${selectedDate.format(DateTimeFormatter.ISO_DATE)}",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(eventsForSelectedDate) { event ->
                EventItem1(
                    event = event,
                    onClick = { /* Handle event click if needed */ }
                )
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun CalendarGrid(
    selectedDate: LocalDate,
    onDateClick: (LocalDate) -> Unit
) {
    val currentMonth = selectedDate.month
    val currentYear = selectedDate.year
    val firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1)
    val lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth())

    // Calculate the day of the week for the first day of the month
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value
    val totalDaysInMonth = lastDayOfMonth.dayOfMonth

    // Prepare days for the grid
    val days = mutableListOf<LocalDate>().apply {
        // Add blank days to align the first day correctly
        repeat(firstDayOfWeek - 1) { add(LocalDate.MIN) }

        // Add actual days of the month
        for (day in 1..totalDaysInMonth) {
            add(LocalDate.of(currentYear, currentMonth, day))
        }
    }

    Column {
        // Weekday header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DayOfWeek.values().take(7).forEach { dayOfWeek ->
                Text(
                    text = dayOfWeek.name.take(3),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        // Calendar Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(days) { date ->
                if (date == LocalDate.MIN) {
                    // Empty placeholder for days before the month starts
                    Box(modifier = Modifier.size(48.dp))
                } else {
                    val isSelected = date == selectedDate
                    val isToday = date == LocalDate.now()

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                color = when {
                                    isSelected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                                    isToday -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                                    else -> Color.Transparent
                                }
                            )
                            .clickable { onDateClick(date) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = when {
                                isSelected -> MaterialTheme.colorScheme.primary
                                isToday -> MaterialTheme.colorScheme.secondary
                                else -> MaterialTheme.colorScheme.onBackground
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EventItem1(
    event: Event1,
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
            // Icon for the event
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
                    imageVector = Icons.Filled.Event,
                    contentDescription = event.title,
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            // Event Title
            Text(
                text = event.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}