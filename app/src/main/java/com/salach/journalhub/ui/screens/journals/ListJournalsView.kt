package com.salach.journalhub.ui.screens.journals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.navigation.graphs.Graph
import com.salach.journalhub.ui.components.journal.JournalCardWIthPreview
import com.salach.journalhub.ui.theme.currentDimensions

@Composable
fun ListJournalsView(
    journals: LiveData<List<Journal>>,
    navController: NavHostController,
    rootController: NavHostController
) {
    val itemsState by journals.observeAsState(emptyList())
    val viewModel: JournalsViewModel = LocalViewModel.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(currentDimensions().XS, Alignment.Top),
        modifier = Modifier
            .fillMaxHeight()
            .padding(currentDimensions().S)
    ){
        itemsIndexed(itemsState){index, journal ->
            JournalCardWIthPreview(
                journal,
                onOpenClicked = {
                    rootController.navigate("${Graph.OPEN_JOURNAL}?journalId=${journals.value!![index].id}")
                },
                onShowClicked = {},
                onAddClicked = {},
                onEditClicked = {
                    rootController.navigate(
                        "${Graph.EDIT_JOURNAL}?journalId=${journals.value!![index].id}"
                    )
                },
                onRemoveClicked = {
                    viewModel.removeJournal(journals.value!![index])
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListJournalsView(){
    val nav = rememberNavController()
    CompositionLocalProvider(LocalViewModel provides provideViewModelForPreview()) {
        ListJournalsView(getJournalsForPreview(), nav, nav)
    }
}
