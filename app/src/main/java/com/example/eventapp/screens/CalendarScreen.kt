package com.example.eventapp.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.*
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
import java.time.Month
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun CalendarScreen(navController: NavHostController) {
    var selectedDate by remember { mutableStateOf<LocalDate>(LocalDate.now()) }
    var eventsForSelectedDate by remember { mutableStateOf<List<Event1>>(emptyList()) }

    // Sample static events
    val events = listOf(
        Event1(LocalDate.parse("2024-11-28"), "Graduation Day", Color(0xFFE6F2FF)),
        Event1(LocalDate.parse("2024-11-29"), "Prize Giving", Color(0xFFFFF3E0)),
        Event1(LocalDate.parse("2024-11-30"), "Sports Day", Color(0xFFF0F4F8)),
        Event1(LocalDate.parse("2024-12-01"), "Sports Gala", Color(0xFFF0E6FF))
    )

    // Display the calendar
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

        // Calendar grid (days of the month)
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

        // LazyColumn for Events
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
                EventItem(event = event, onClick = {
                    // Handle event click if needed
                })
            }
        }
    }
}

// Custom Calendar Grid
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

    val days = mutableListOf<LocalDate>()
    // Add blank days to the beginning of the grid (to align the first day with the correct weekday)
    for (i in 1 until firstDayOfWeek) {
        days.add(LocalDate.MIN)  // Add empty days
    }
    // Add the actual days of the month
    for (day in 1..totalDaysInMonth) {
        days.add(LocalDate.of(currentYear, currentMonth, day))
    }

    // Display the calendar
    Column {
        // Weekday header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DayOfWeek.values().forEach {
                Text(
                    text = it.name.take(3), // Show abbreviated weekday names (Mon, Tue, etc.)
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Grid of days


    }
}

// Sample Event Data
data class Event1(
    val date: LocalDate,  // Changed to LocalDate for consistency
    val title: String,
    val backgroundColor: Color
)

@Composable
fun EventItem(
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
