package com.example.eventapp

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Notifications : Screen("notifications")
    object Calendar : Screen("calendar")
    object Profile : Screen("profile")
    object SignIn : Screen("sign_in")
    object SignUp : Screen("sign_up")
    object Schools : Screen("schools")
    object Events : Screen("events/{title}/{date}/{location}") {
        fun createRoute(title: String, date: String, location: String): String {
            return "events/$title/$date/$location"
        }
    }
}
