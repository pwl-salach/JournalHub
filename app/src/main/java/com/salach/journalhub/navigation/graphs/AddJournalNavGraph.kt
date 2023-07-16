package com.salach.journalhub.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.screens.journal.add.AddJournalInitScreen
import com.salach.journalhub.ui.screens.journal.add.PickJournalColor
import com.salach.journalhub.ui.screens.journal.add.PickJournalIcon
import com.salach.journalhub.ui.screens.journal.add.PickJournalIconColor

@Composable
fun AddJournalNavGraph(journal: MutableState<Journal>, navController: NavHostController){
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
        composable(route = Route.AddJournalPickIcon.link){
            PickJournalIcon(journal)
        }
        composable(route = Route.AddJournalPickIconColor.link){
            PickJournalIconColor(journal)
        }
    }
}
