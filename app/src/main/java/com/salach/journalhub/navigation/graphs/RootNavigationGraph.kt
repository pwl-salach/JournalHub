package com.salach.journalhub.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.salach.journalhub.ui.screens.MainScreen
import com.salach.journalhub.ui.screens.journal.add.AddJournalScreen
import com.salach.journalhub.ui.screens.journals.ProvideJournalViewModel

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME,
    ){
        composable(route = Graph.HOME){
            MainScreen(navController)
        }
        composable(
            route = "${Graph.NEW_JOURNAL}?journalId={journalId}",
            arguments = listOf(navArgument("journalId") {
                type = NavType.IntType
                defaultValue = -1
            })
        ){ backStackEntry ->
            val journalId = backStackEntry.arguments?.getInt("journalId", -1)
            ProvideJournalViewModel {
                AddJournalScreen(navController, journalId)
            }
        }

    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val NEW_JOURNAL = "new_journal_graph"
}
