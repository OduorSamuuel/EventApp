package com.example.eventapp.components

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.eventapp.Screen
import com.example.eventapp.screens.CalendarScreen
import com.example.eventapp.screens.EventsScreen
import com.example.eventapp.screens.HomeScreen
import com.example.eventapp.screens.NotificationsScreen
import com.example.eventapp.screens.ProfileScreen
import com.example.eventapp.screens.SchoolsScreen
import com.example.eventapp.screens.SignInScreen
import com.example.eventapp.screens.SignUpScreen

@Composable
fun NavigationComponent(
    navController: NavHostController
) {
    // Track the current route to manage navigation state
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    // Define navigation items as a data structure
    val navigationItems = listOf(
        NavigationItem(
            route = Screen.Home.route,
            icon = Icons.Default.Home,
            label = "Home",
            contentDescription = "Home"
        ),
        NavigationItem(
            route = Screen.Notifications.route,
            icon = Icons.Default.Notifications,
            label = "Notifications",
            contentDescription = "View Notifications"
        ),
        NavigationItem(
            route = Screen.Calendar.route,
            icon = Icons.Default.CalendarToday,
            label = "Calendar",
            contentDescription = "View Calendar Events"
        ),
        NavigationItem(
            route = Screen.Profile.route,
            icon = Icons.Default.Person,
            label = "Profile",
            contentDescription = "View Profile"
        )
    )

    // Define the routes where the bottom navigation bar should be displayed
    val routesWithBottomNav = setOf(
        Screen.Home.route,
        Screen.Notifications.route,
        Screen.Calendar.route,
        Screen.Profile.route
    )

    Scaffold(
        bottomBar = {
            // Only show the bottom navigation bar for specific routes
            if (currentRoute in routesWithBottomNav) {
                NavigationBar {
                    navigationItems.forEach { item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.contentDescription
                                )
                            },
                            label = { Text(item.label) },
                            selected = currentRoute == item.route,
                            onClick = {
                                navigateToRoute(
                                    navController,
                                    item.route,
                                    currentRoute
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        // NavHost with all composable routes
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            // Home Screen
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    // paddingValues = paddingValues
                )
            }

            // Notifications Screen
            composable(Screen.Notifications.route) {
                NotificationsScreen(
                    navController = navController,
                    // paddingValues = paddingValues
                )
            }

            // Calendar Screen
            composable(Screen.Calendar.route) {
                CalendarScreen(
                    navController = navController,
                    //paddingValues = paddingValues
                )
            }

            // Profile Screen
            composable(Screen.Profile.route) {
                ProfileScreen(
                    navController = navController,
                    // paddingValues = paddingValues
                )
            }

            // Additional Screens
            composable(Screen.SignIn.route) {
                SignInScreen(navController = navController)
            }

            composable(Screen.SignUp.route) {
                SignUpScreen(navController = navController)
            }

            composable(Screen.Schools.route) {
                SchoolsScreen(navController = navController)
            }

            composable(
                route = Screen.Events.route,
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType },
                    navArgument("date") { type = NavType.StringType },
                    navArgument("location") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                val date = backStackEntry.arguments?.getString("date") ?: ""
                val location = backStackEntry.arguments?.getString("location") ?: ""

                EventsScreen(
                    title = title,
                    date = date,
                    location = location,
                    navController = navController,
                    onAddToNotifications = {},
                    onAddGuest = {},
                    onRemoveGuest = {}


                )
            }
        }
    }

}


/**
 * Data class to represent navigation items
 */
data class NavigationItem(
    val route: String,
    val icon: ImageVector,
    val label: String,
    val contentDescription: String
)

/**
 * Helper function for consistent and safe navigation
 */
private fun navigateToRoute(
    navController: NavHostController,
    route: String,
    currentRoute: String?
) {
    try {
        // Only navigate if not already on the target route
        if (currentRoute != route) {
            navController.navigate(route) {
                // Pop up to the start destination to avoid building a large navigation stack
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination
                launchSingleTop = true
                // Restore state when re-selecting the same item
                restoreState = true
            }
        }
    } catch (e: Exception) {
        // Log any navigation errors
        Log.e("NavigationComponent", "Navigation error to $route", e)
    }
}
