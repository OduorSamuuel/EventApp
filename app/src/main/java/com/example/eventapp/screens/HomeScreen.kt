package com.example.eventapp.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.eventapp.Screen
import com.example.eventapp.components.TopAppBar

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TopAppBar(
                navController = navController
            )
            Spacer(modifier = Modifier.height(16.dp))
            SchoolsSection(navController)
            Spacer(modifier = Modifier.height(16.dp))
            EventsSection(navController)
            Spacer(modifier = Modifier.height(16.dp))
            MostRecentSection()
        }
    }
}



@Composable
private fun SchoolsSection(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SchoolItem(
            navController = navController,
            imageUrl = "https://via.placeholder.com/60",
            schoolName = "SCES"
        )
        SchoolItem(
            navController = navController,
            imageUrl = "https://via.placeholder.com/60",
            schoolName = "SHSS"
        )
        SchoolItem(
            navController = navController,
            imageUrl = "https://via.placeholder.com/60",
            schoolName = "SIMS"
        )
        SchoolItem(
            navController = navController,
            imageUrl = "https://via.placeholder.com/60",
            schoolName = "SUBS"
        )
    }
}

@Composable
private fun SchoolItem(
    navController: NavHostController,
    imageUrl: String,
    schoolName: String
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .padding(8.dp)
            .clickable {
                navController.navigate(Screen.Schools.route)
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "School Logo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
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
                imageUrl = "https://via.placeholder.com/160x120",
                navController = navController
            )
            EventCard(
                title = "Sports Gala",
                date = "January 7th",
                location = "Sports Field",
                imageUrl = "https://via.placeholder.com/160x120",
                navController = navController
            )
            EventCard(
                title = "PTA Meeting",
                date = "January 12th",
                location = "Conference Room",
                imageUrl = "https://via.placeholder.com/160x120",
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
private fun MostRecentSection() {
    Column {
        Text(
            text = "Most Recent",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        }
    }
}