package com.example.eventapp.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.eventapp.R
import com.example.eventapp.Screen
import com.example.eventapp.components.TopAppBar

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Scrollable content that goes under the TopAppBar
        ScrollableContent(navController)

        // Fixed TopAppBar
        TopAppBar(
            navController = navController,

        )
    }
}
@Composable
fun ScrollableContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Enable scrolling for this content
            .padding(top = 56.dp, start = 16.dp, end = 16.dp) // Combine padding into one modifier
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SchoolsSection(navController)
        Spacer(modifier = Modifier.height(16.dp))
        EventsSection(navController)
        Spacer(modifier = Modifier.height(16.dp))
        MostRecentSection(navController)
    }
}



@Composable
private fun SchoolsSection(navController: NavHostController) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Our Schools",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Show all",
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.clickable { navController.navigate(Screen.Schools.route) }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SchoolItem(
                navController = navController,
                imageResource = R.drawable.sces,
                schoolName = "SCES",
                schoolDescription = "Comprehensive"
            )
            SchoolItem(
                navController = navController,
                imageResource = R.drawable.shss,
                schoolName = "SHSS",
                schoolDescription = "Humanities"
            )
            SchoolItem(
                navController = navController,
                imageResource = R.drawable.sims,
                schoolName = "SIMS",
                schoolDescription = "Management"
            )


        }
    }
}


@Composable
fun SchoolItem(
    navController: NavHostController,
    imageResource: Int, // Use Int for resource ID
    schoolName: String,
    schoolDescription: String
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                // Handle item click (navigate to specific school details page)
                navController.navigate(Screen.Schools.route)
            }
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = schoolName,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = schoolName,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = schoolDescription,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun EventsSection(navController: NavHostController) {
    Column {
        Text(
            text = "Top Events",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EventCard(
                title = "Prize Giving Day",
                date = "January 5th",
                location = "Graduation Square",
                imageUrl = "https://images.pexels.com/photos/7842778/pexels-photo-7842778.jpeg?auto=compress&cs=tinysrgb&w=600",
                navController = navController
            )
            EventCard(
                title = "Sports Gala",
                date = "January 7th",
                location = "Sports Field",
                imageUrl = "https://images.pexels.com/photos/29546388/pexels-photo-29546388/free-photo-of-remote-weather-station-in-glasgow-montana.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                navController = navController
            )
            EventCard(
                title = "PTA Meeting",
                date = "January 12th",
                location = "Conference Room",
                imageUrl = "https://images.pexels.com/photos/5905892/pexels-photo-5905892.jpeg?auto=compress&cs=tinysrgb&w=600",
                navController = navController
            )
        }
    }
}


@Composable
private fun EventCard(
    title: String,
    date: String,
    location: String,
    imageUrl: String,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            navController.navigate(
                Screen.Events.createRoute(
                    title = title,
                    date = date,
                    location = location
                )
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Event Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0x80000000)
                            )
                        )
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
private fun MostRecentSection(navController: NavHostController) {
    Column {
        Text(
            text = "Most Recent",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RecentCard(
                title = "Science Fair Winners",
                date = "November 22nd",
                location = "SIMS Auditorium",
                imageUrl = "https://images.pexels.com/photos/25626433/pexels-photo-25626433/free-photo-of-human-responsibility.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                navController = navController
            )
            RecentCard(
                title = "Debate Championship",
                date = "November 20th",
                location = "SHSS Hall",
                imageUrl = "https://images.pexels.com/photos/5668473/pexels-photo-5668473.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                navController = navController
            )
            RecentCard(
                title = "Sports Day Highlights",
                date = "November 18th",
                location = "SCES Grounds",
                imageUrl = "https://images.pexels.com/photos/716276/pexels-photo-716276.jpeg",
                navController = navController
            )
        }
    }
}

@Composable
private fun RecentCard(
    title: String,
    date: String,
    location: String,
    imageUrl: String,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            navController.navigate(
                Screen.Events.createRoute(
                    title = title,
                    date = date,
                    location = location
                )
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Most Recent Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0x80000000)
                            )
                        )
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    )
                )
                Text(
                    text = location,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White
                    )
                )
            }
        }
    }
}
