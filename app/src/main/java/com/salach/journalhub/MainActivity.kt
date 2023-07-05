package com.salach.journalhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.ui.screens.dashboard.DashboardScreen
import com.salach.journalhub.ui.screens.dashboard.ProvideDashboardViewModel
import com.salach.journalhub.ui.screens.journal.add.AddJournalScreen
import com.salach.journalhub.ui.screens.journals.JournalsScreen
import com.salach.journalhub.ui.screens.journals.ProvideJournalViewModel
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val activeScreen = remember { mutableStateOf<Screen?>(null) }

    LaunchedEffect(navController) {
        val navBackStackEntryFlow = navController.currentBackStackEntryFlow
        navBackStackEntryFlow.collect { navBackStackEntry ->
            val currentRoute = navBackStackEntry.destination.route
            activeScreen.value = getScreenByRoute(currentRoute)
        }
    }

    if (activeScreen.value?.route != "addJournal"){
        RegularBottomNavigation(navController)
    } else {
        AddJournalBottomBar(navController)
    }
}

fun getScreenByRoute(route: String?): Screen? {
    // Define your screen list with routes and return the corresponding screen for a given route
    val screenList = listOf(
        Screen("dashboard", Icons.Default.Home, "Dashboard"),
        Screen("journals", Icons.Default.Person, "Journals"),
        Screen("settings", Icons.Default.Settings, "Settings"),
        Screen("addJournal", Icons.Default.Add, "Add Journal")
    )
    return screenList.find { it.route == route }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegularBottomNavigation(navController: NavHostController){
    val items = listOf(
        Screen("dashboard", Icons.Default.Home, "Dashboard"),
        Screen("Journals", Icons.Default.Person, "Journals"),
        Screen("settings", Icons.Default.Settings, "Settings")
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.height(Dimensions.bottomBarHeight)
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
            composable("addJournal") { AddJournalScreen()}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJournalBottomBar(navController: NavHostController){
    Scaffold(bottomBar = {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimensions.bottomBarHeight)
                .background(color = ColorPalette.FrenchGray20)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_x),
                contentDescription = "cancel",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = "Step 1/6",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                textAlign = TextAlign.Right,
                letterSpacing = 0.14.sp
            )
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }) {
        Box(modifier = Modifier.padding(it)){
            AddJournalScreen()
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
