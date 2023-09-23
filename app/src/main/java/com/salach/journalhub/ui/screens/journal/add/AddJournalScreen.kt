package com.salach.journalhub.ui.screens.journal.add

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.navigation.bars.AddJournalBottomBar
import com.salach.journalhub.navigation.graphs.AddJournalNavGraph
import com.salach.journalhub.ui.components.popups.QuitEditingPrompt
import com.salach.journalhub.ui.screens.journals.JournalsViewModel
import com.salach.journalhub.ui.screens.journals.LocalViewModel
import java.time.LocalDate


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddJournalScreen(
    rootController: NavHostController,
    journalId: Int?
) {
    val viewModel: JournalsViewModel = LocalViewModel.current
    val journalState = remember { mutableStateOf(
        Journal("", "", createdDate = LocalDate.now(), editedDate = LocalDate.now())
    )}
    var showPopup by remember { mutableStateOf(false) }

    journalId?.let { id ->
        val journalLiveData = viewModel.getJournal(id)
        val journal = journalLiveData.observeAsState().value
        journal?.let { dbJournal ->
            journalState.value = dbJournal
        }
    }

    val nestedNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            AddJournalBottomBar(
                rootController, nestedNavController,
                cancelFlowCallback = {
                    showPopup = true
                },
                finishedFlowCallback = {
                    if (journalId != null && journalId != -1){
                        viewModel.updateJournal(journalState.value)
                    } else {
                        viewModel.addJournal(journalState.value)
                    }
                    rootController.popBackStack()
                }
            )
        }
    ) {
        AddJournalNavGraph(journalState, nestedNavController)
    }

    QuitEditingPrompt(
        openPopup = showPopup,
        onCancel = { showPopup = false },
        onConfirm = { rootController.popBackStack() }
    )
}
