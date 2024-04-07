package com.salach.journalhub.ui.screens.journal.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.salach.journalhub.R
import com.salach.journalhub.navigation.graphs.Graph
import com.salach.journalhub.ui.components.basic.IconButton
import com.salach.journalhub.ui.components.PageMiniature
import com.salach.journalhub.ui.components.journal.JournalCardWIthPreview
import com.salach.journalhub.ui.screens.pages.PagesViewModel
import com.salach.journalhub.ui.theme.currentDimensions

val LocalPagesViewModel = compositionLocalOf<PagesViewModel> {
    error("No ViewModel provided")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalPagesGridScreen(journalId: Int, navController: NavHostController) {
    val viewModel: PagesViewModel = LocalPagesViewModel.current
    val journal = viewModel.getJournal(journalId).observeAsState()
    val itemsState by viewModel.getPages(journalId).observeAsState(emptyList())

    val dimensions = currentDimensions()
    val itemsPerRow = 4
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(iconId = R.drawable.ic_arrow_back, description = "Back" ) {
                        navController.popBackStack()
                    }
                 },
                title = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            journal.value?.let {
                Box(
                    modifier = Modifier.padding(dimensions.S)
                ){
                    JournalCardWIthPreview(
                        it,
                        onOpenClicked = {},
                        onShowClicked = {},
                        onAddClicked = {},
                        onEditClicked = {
                            navController.navigate(
                                "${Graph.EDIT_JOURNAL}?journalId=${it.id}"
                            )
                        },
                        onRemoveClicked = {
                            viewModel.removeJournal(it)
                        }
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(vertical = dimensions.S)
            ) {
                items(itemsState.windowed(itemsPerRow, itemsPerRow, true)){rowItems ->
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.Start),
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(horizontal = dimensions.S)
                    ) {
                        items(rowItems) { page ->
                            PageMiniature(
                                page = page,
                                isSelectable = false,
                                onClick = {
                                    navController.navigate(
                                        "${Graph.NOTE_PAGE}?journalId=${journalId}&pageId=${page.id}&pageType=${page.type}"
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
