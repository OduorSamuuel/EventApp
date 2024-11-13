import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost

class MainActivity : ComponentActivity() {
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
            Text("See All", color = Color.Gray)
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
        Text("SCES Events", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        repeat(4) {
            EventListItem()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSchoolPage() {
    SchoolPage(navController = rememberNavController())
}

@Composable
fun EventListItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Event Name")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEventListItem() {
    EventListItem()
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

