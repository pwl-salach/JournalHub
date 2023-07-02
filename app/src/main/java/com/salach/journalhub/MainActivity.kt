package com.salach.journalhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.ui.screens.dashboard.DashboardScreen
import com.salach.journalhub.ui.screens.dashboard.ProvideDashboardViewModel
import com.salach.journalhub.ui.screens.journals.JournalsScreen
import com.salach.journalhub.ui.screens.journals.ProvideJournalViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val items = listOf(
        Screen("dashboard", Icons.Default.Home, "Dashboard"),
        Screen("Journals", Icons.Default.Person, "Journals"),
        Screen("settings", Icons.Default.Settings, "Settings")
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
            ) {
                val currentRoute = navController.currentDestination?.route
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Add any desired navigation options
                            }
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Handle FAB click
                    navController.navigate("addJournal") {
                        // Add any desired navigation options
                    }
                },
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Journal")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("dashboard") { ProvideDashboardViewModel{ DashboardScreen(navController) }}
            composable("journals") { ProvideJournalViewModel {JournalsScreen(navController) }}
            composable("settings") { SettingsScreen(navController) }
        }
    }
}

data class Screen(val route: String, val icon: ImageVector, val title: String)


@Composable
fun ProfileScreen(navController: NavHostController) {
    Text(text = "ProfileScreen")
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    Text(text = "SettingsScreen")
}
