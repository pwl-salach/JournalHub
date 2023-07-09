package com.salach.journalhub.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.SettingsScreen
import com.salach.journalhub.ui.screens.dashboard.DashboardScreen
import com.salach.journalhub.ui.screens.dashboard.ProvideDashboardViewModel
import com.salach.journalhub.ui.screens.journal.add.AddJournalScreen
import com.salach.journalhub.ui.screens.journals.JournalsScreen
import com.salach.journalhub.ui.screens.journals.ProvideJournalViewModel

@Composable
fun TabNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = Route.Dashboard.link,
    ) {
        composable(route = Route.Dashboard.link) {
            ProvideDashboardViewModel {
                DashboardScreen(navController)
            }
        }
        composable(route = Route.Journals.link) {
            ProvideJournalViewModel {
                JournalsScreen(navController)
            }
        }
        composable(route = Route.Settings.link) {
            SettingsScreen(navController)
        }
    }
}

