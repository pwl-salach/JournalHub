package com.salach.journalhub.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.salach.journalhub.enums.PageType
import com.salach.journalhub.ui.screens.MainScreen
import com.salach.journalhub.ui.screens.journal.add.AddJournalScreen
import com.salach.journalhub.ui.screens.journals.ProvideJournalViewModel
import com.salach.journalhub.ui.screens.pages.ProvidePagesViewModel
import com.salach.journalhub.ui.screens.pages.ViewJournalPage

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
        composable(
            route = "${Graph.NOTE_PAGE}?journalId={journalId}&pageId={pageId}&newPageType={newPageType}",
            arguments = listOf(
                navArgument("journalId"){
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("pageId"){
                    type = NavType.LongType
                    defaultValue = -1
                },
                navArgument("newPageType") {
                    type = NavType.StringType
                    defaultValue = PageType.NOTE.name
                }
            )
        ) {backStackEntry ->
            val journalId = backStackEntry.arguments?.getInt("journalId", 0) ?: 0
            val pageId = backStackEntry.arguments?.getLong("pageId", -1L) ?: -1L
            val pageType = PageType.valueOf(
                backStackEntry.arguments?.getString("newPageType", PageType.NOTE.name)?: PageType.NOTE.name
            )

            ProvidePagesViewModel {
                ViewJournalPage(journalId = journalId, pageId = pageId, newPageType = pageType, navController = navController)
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val NEW_JOURNAL = "new_journal_graph"
    const val NOTE_PAGE = "note_page_graph"
}
