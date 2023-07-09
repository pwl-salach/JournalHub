package com.salach.journalhub.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.screens.journal.add.AddJournalInitScreen
import com.salach.journalhub.ui.screens.journal.add.PickJournalColor

@Composable
fun AddJournalNavGraph(navController: NavHostController){
    val journal = remember { mutableStateOf(Journal("", "")) }

    NavHost(
        navController = navController,
        route = Graph.NEW_JOURNAL,
        startDestination = Route.AddJournalInit.link
    ){
        composable(route = Route.AddJournalInit.link) {
            AddJournalInitScreen(journal)
        }
        composable(route = Route.AddJournalPickColor.link) {
            PickJournalColor(journal)
        }
    }
}
