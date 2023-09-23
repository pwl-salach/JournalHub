package com.salach.journalhub.ui.screens.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.JournalHub
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.enums.PageType
import com.salach.journalhub.navigation.graphs.Graph
import com.salach.journalhub.ui.screens.journal.view.LocalPagesViewModel
import com.salach.journalhub.ui.screens.pages.components.JournalPageBottomBar
import com.salach.journalhub.ui.screens.pages.components.JournalPageHeader
import com.salach.journalhub.ui.screens.pages.components.JournalPageTopBar
import com.salach.journalhub.ui.screens.pages.note.EditNote
import com.salach.journalhub.ui.screens.pages.note.ViewNote
import java.time.LocalDate

@Composable
fun ViewJournalPage(
    journalId: Int,
    pageId: Long?,
    newPageType: PageType,
    navController: NavHostController
) {
    val editMode = remember { mutableStateOf(false) }

    val viewModel: PagesViewModel = LocalPagesViewModel.current
    val itemsState by viewModel.getPages(journalId).observeAsState(emptyList())
    val page = remember { mutableStateOf(
        Page(journalId, "", LocalDate.now(), type = newPageType)
    )}
    var prevPageId: Long = -1L
    var nextPageId: Long = -1L
    var currentIndex = 0
    if (pageId != -1L){
        viewModel.getPages(journalId).value?.let { journalPages ->
            for ((index, iterPage) in journalPages.withIndex()){
                if(iterPage.id == pageId){
                    page.value = iterPage
                    currentIndex = index
                    if(journalPages.count() > index + 1){
                        nextPageId = journalPages[index + 1].id!!
                    }
                    break
                } else {
                    prevPageId = iterPage.id!!
                }
            }
        }

        viewModel.getPages(journalId).value?.find{ it.id == pageId }?.let {
            page.value = it
        }
    } else {
        editMode.value = true
    }
    val note = remember {
        mutableStateOf(
            page.value.id?.let {
                viewModel.loadPage<Note>(it, page.value.type)
            } ?: Note(-1, "")
        )
    }

    Scaffold(
        topBar = {
            JournalPageTopBar(
                editMode = editMode.value,
                backOnClick = { navController.popBackStack() },
                modeOnClick = {
                    if(editMode.value){
                        viewModel.saveNote<Note>(page.value, note.value)
                    }
                    editMode.value = !editMode.value
                },
                addScheduleOnClick = {},
                optionsOnClick = {}
            )
        },
        bottomBar = {
            if (!editMode.value){
                JournalPageBottomBar(
                    currentIndex = currentIndex,
                    pagesCount = itemsState.count(),
                    viewPrevious = {
                        navController.navigate("${Graph.NOTE_PAGE}?journalId=${journalId}&pageId=${prevPageId}")
                    },
                    viewNext = {
                        navController.navigate("${Graph.NOTE_PAGE}?journalId=${journalId}&pageId=${nextPageId}")
                    }
                )
            }
        }
    ) {
        Column() {
            JournalPageHeader(page = page.value, paddingValues = it)
            if(page.value.type == PageType.NOTE){
                if(!editMode.value){
                    ViewNote(note)
                } else {
                    EditNote(note)
                }
            }
        }
    }
}

@Composable
fun ProvidePagesViewModel(content: @Composable () -> Unit){
    val context = LocalContext.current
    val app = context.applicationContext as JournalHub
    val viewModel: PagesViewModel = viewModel(
        factory = PagesViewModelFactory(
            app.pagesRepository,
            app.journalsRepository
        )
    )
    CompositionLocalProvider(LocalPagesViewModel provides viewModel) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewViewJournalPage(){
    val nav = rememberNavController()
    CompositionLocalProvider(LocalPagesViewModel provides provideViewModelForPreview()) {
        ViewJournalPage(0,0, PageType.NOTE, nav)
    }
}