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
import com.salach.journalhub.ui.screens.journal.view.JournalPagesGridScreen
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
            route = "${Graph.EDIT_JOURNAL}?journalId={journalId}",
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
            route = "${Graph.OPEN_JOURNAL}?journalId={journalId}",
            arguments = listOf(navArgument("journalId") {
                type = NavType.IntType
                defaultValue = -1
            })
        ){ backStackEntry ->
            val journalId = backStackEntry.arguments?.getInt("journalId", -1) ?: -1
            ProvidePagesViewModel {
                JournalPagesGridScreen(journalId, navController)
            }
        }
        composable(
            route = "${Graph.NOTE_PAGE}?journalId={journalId}&pageId={pageId}&pageType={pageType}",
            arguments = listOf(
                navArgument("journalId"){
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("pageId"){
                    type = NavType.LongType
                    defaultValue = -1
                },
                navArgument("pageType") {
                    type = NavType.StringType
                    defaultValue = PageType.NOTE.name
                }
            )
        ) {backStackEntry ->
            val journalId = backStackEntry.arguments?.getInt("journalId", 0) ?: 0
            val pageId = backStackEntry.arguments?.getLong("pageId", -1L) ?: -1L
            val pageType = PageType.valueOf(
                backStackEntry.arguments?.getString("pageType", PageType.NOTE.name)?: PageType.NOTE.name
            )

            ProvidePagesViewModel {
                ViewJournalPage(journalId, pageId, pageType, navController)
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val EDIT_JOURNAL = "edit_journal_graph"
    const val OPEN_JOURNAL = "open_journal"
    const val NOTE_PAGE = "note_page_graph"
}
