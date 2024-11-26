package com.example.eventapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale

data class Event(
    val name: String,
    val date: String,
    val description: String,
    val poster_url: String
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventHubApp()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        EventHubApp()
    }

    @Composable
    fun EventHubApp() {
        val events = listOf(
            Event("Art Exhibition", "Dec 15, 2024", "An exhibition showcasing modern art.", ""),
            Event(
                "Tech Talk",
                "Dec 20, 2024",
                "A tech talk by industry leaders.",
                "https://example.com/tech_talk.jpg"
            ),
            Event(
                "Food Festival",
                "Dec 25, 2024",
                "Enjoy local and international cuisines.",
                "https://example.com/food_festival.jpg"
            )
        )
        EventList(events = events)
    }

    @Composable
    fun EventList(events: List<Event>) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(events) { event ->
                EventItem(event = event)
            }
        }
    }

    @Composable
    fun EventItem(event: Event) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { /* Handle event click here */ }
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                // Use Image as before
                Image(
                    painter = rememberImagePainter(event.poster_url),
                    contentDescription = "Event Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = event.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = event.date,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {}
                ) {
                    Text("View Details")
                }
            }
        }
    }

    @Composable
    fun EventDetailsScreen(event: Event) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Event Image (poster)
            Image(
                painter = rememberImagePainter(event.poster_url),
                contentDescription = "Event Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Event Title
            Text(
                text = event.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Event Date
            Text(
                text = event.date,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Event Description (if available)
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Back Button to navigate back
            Button(
                onClick = { /* Implement back navigation logic */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Back to Events")
            }
        }
    }

    @Preview
    @Composable
    fun PreviewEventDetailsScreen() {
        val sampleEvent = Event(
            name = "Graduation Ceremony",
            date = "December 15, 2024",
            description = "Join us for the graduation ceremony of the class of 2024.",
            poster_url = "https://example.com/poster.jpg"
        )

        EventDetailsScreen(event = sampleEvent)
    }
}
