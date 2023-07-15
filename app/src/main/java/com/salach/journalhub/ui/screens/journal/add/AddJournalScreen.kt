package com.salach.journalhub.ui.screens.journal.add

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.navigation.bars.AddJournalBottomBar
import com.salach.journalhub.navigation.graphs.AddJournalNavGraph
import com.salach.journalhub.navigation.graphs.Route
import com.salach.journalhub.navigation.graphs.TabNavGraph
import com.salach.journalhub.ui.screens.journals.JournalsViewModel
import com.salach.journalhub.ui.screens.journals.LocalViewModel
import java.time.LocalDate


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJournalScreen(rootController: NavHostController) {
    val viewModel: JournalsViewModel = LocalViewModel.current
    val journal = remember { mutableStateOf(
        Journal("", "", createdDate = LocalDate.now(), editedDate = LocalDate.now())
    )}
    val nestedNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            AddJournalBottomBar(rootController, nestedNavController){
                viewModel.addJournal(journal.value)
            }
        }
    ) {
        AddJournalNavGraph(journal, nestedNavController)
    }
}
