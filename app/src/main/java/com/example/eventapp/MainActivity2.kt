package com.example.eventapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import coil.compose.rememberImagePainter

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("homepage") { HomePage(navController) }
        composable("schoolPage") { SchoolPage(navController) }
        composable("eventDetails") { EventDetailsPage(navController) }
        composable("notifications") { NotificationsPage(navController) }
        composable("eventhub") { EventHubApp(navController)  }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MyApp()
}

@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sign In", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email Address") })
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("homepage") }) {
            Text("Continue")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { /* Google sign-in logic */ }) {
            Text("Continue With Google")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}

@Composable
fun HomePage(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Schools", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Button(onClick = {navController.navigate("SchoolPage") }) {
                Text("See All")
            }
        }
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.LightGray, RoundedCornerShape(25.dp))
                )
            }
        }
        Text("Top Events", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
        repeat(2) {
            EventCard(navController)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Most Recent", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
        repeat(2) {
            EventCard(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomePage() {
    HomePage(navController = rememberNavController())
}

@Composable
fun EventCard(navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Event Name")
            Text("Event Date", color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navController.navigate("eventDetails") }) {
                Text("View Details")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEventCard() {
    EventCard(navController = rememberNavController())
}

@Composable
fun SchoolPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Schools", fontSize = 20.sp)
        SchoolList(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSchoolPage() {
    SchoolPage(navController = rememberNavController())
}

@Composable
fun SchoolListItem(schoolName: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth() // Makes button take full width if needed
                    .padding(0.dp)
                    .size(60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = schoolName)
            }
        }
    }
}

@Composable
fun SchoolList(navController: NavController) {
    val schoolNames = listOf(
        "Strathmore University",
        "Strathmore Institute of Management and Technology (SI)",
        "Strathmore Institute of Mathematical Sciences (SIMS)",
        "School of Tourism and Hospitality (STH)",
        "School of Humanities and Social Sciences (SHSS)",
        "School of Computing and Engineering Sciences (SCES)",
        "Strathmore Law School (SLS)",
        "Strathmore Business School (SBS)",
        "Strathmore Clubs Events"
    )

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(schoolNames) { schoolName ->
            SchoolListItem(schoolName = schoolName) {
                // Navigate to "eventDetails" screen with NavController
                navController.navigate("eventhub")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SchoolListPreview() {
    // Placeholder for NavController in preview
    val dummyNavController = rememberNavController()
    SchoolList(navController = dummyNavController)
}


@Composable
fun EventDetailsPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Graduation Day", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("January 7th", color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Location: Graduation Square")
        Text("School: SCES")
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Guests")
            Button(onClick = { /* Add to notifications */ }) {
                Text("Add to Notifications")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEventDetailsPage() {
    EventDetailsPage(navController = rememberNavController())
}

@Composable
fun NotificationsPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Notifications", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("No Notification yet")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("homepage") }) {
            Text("Explore Events")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotificationsPage() {
    NotificationsPage(navController = rememberNavController())
}

@Composable
fun EventHubApp(navController: NavHostController) {
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

