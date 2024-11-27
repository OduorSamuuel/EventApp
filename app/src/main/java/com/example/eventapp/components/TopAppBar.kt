package com.example.eventapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.eventapp.R
import com.example.eventapp.Screen
import com.example.eventapp.utils.AuthManager

@Composable
fun TopAppBar(
    navController: NavHostController,

) {
    val context = LocalContext.current
    val authManager = remember { AuthManager(context) }

    // State to control dropdown menu visibility
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            //.align(Alignment.TopCenter)
            .zIndex(1f)  ,


        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left: Title
        Text(
            text = "Event Hub",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        // Right: Profile Icon and Dropdown
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Circular Profile Image Placeholder
                Image(
                    painter = painterResource(id = R.drawable.law), // Replace with your placeholder image
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.Gray) // Gray background as placeholder
                )

                // Small "Hello, [Name]" Text
                Text(
                    text = if (authManager.isLoggedIn())
                        "Hello, ${authManager.getUsername()}"
                    else "Hello, Guest",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Vertical Menu Icon for Dropdown
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu"
                )
            }

            // Dropdown Menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                if (authManager.isLoggedIn()) {
                    // Profile Option
                    DropdownMenuItem(
                        text = { Text("Profile") },
                        onClick = {
                            navController.navigate(Screen.Profile.route)
                            expanded = false
                        }
                    )
                    // Logout Option
                    DropdownMenuItem(
                        text = { Text("Logout") },
                        onClick = {
                            authManager.signOut()
                            navController.navigate(Screen.SignIn.route) {
                                popUpTo(Screen.Home.route) { inclusive = true }
                            }
                            expanded = false
                        }
                    )
                } else {
                    // Login Option
                    DropdownMenuItem(
                        text = { Text("Login") },
                        onClick = {
                            navController.navigate(Screen.SignIn.route)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
