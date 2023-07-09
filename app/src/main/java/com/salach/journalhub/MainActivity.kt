package com.salach.journalhub

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.navigation.graphs.RootNavigationGraph
import com.salach.journalhub.ui.screens.MainScreen
import com.salach.journalhub.ui.theme.JournalHubTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JournalHubTheme{
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}




data class Screen(val route: String, val icon: Int, val title: String)

//fun getScreenByRoute(route: String?): Screen? {
//    // Define your screen list with routes and return the corresponding screen for a given route
//    val screenList = listOf(
//        Screen("dashboard", Icons.Default.Home, "Dashboard"),
//        Screen("journals", Icons.Default.Person, "Journals"),
//        Screen("settings", Icons.Default.Settings, "Settings"),
//        Screen("addJournalInit", Icons.Default.Add, "Add Journal")
//    )
//    return screenList.find { it.route == route }
//}



@Composable
fun SettingsScreen(navController: NavHostController) {
    Text(text = "SettingsScreen")
}
