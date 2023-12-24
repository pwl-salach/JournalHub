package com.salach.journalhub.ui.screens.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
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
    val journalPages by viewModel.getPages(journalId).observeAsState(emptyList())
    val page = remember { mutableStateOf(
        Page(journalId, "", LocalDate.now(), type = newPageType)
    )}
    var prevPageId: Long = -1L
    var nextPageId: Long = -1L
    var currentIndex = 0
    // FIXME support other types of pages
    val note = remember { mutableStateOf(Note(id=-1, text= AnnotatedString(""))) }
    if (pageId != -1L){
        for ((index, iterPage) in journalPages.withIndex()){
            if(iterPage.id == pageId){
                page.value = iterPage
                page.value.id?.let { pageId ->
                    viewModel.loadPage<Note>(pageId, page.value.type)?.observeAsState()?.value?.let {
                        note.value = it
                    }
                }
                currentIndex = index
                if(journalPages.count() > index + 1){
                    nextPageId = journalPages[index + 1].id!!
                }
                break
            } else {
                prevPageId = iterPage.id!!
            }
        }
    } else {
        editMode.value = true
    }
    Scaffold(
        topBar = {
            JournalPageTopBar(
                editMode = editMode.value,
                backOnClick = { navController.popBackStack() },
                modeOnClick = {
                    if(editMode.value){
                        viewModel.saveNote(page.value, note.value)
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
                    pagesCount = journalPages.count(),
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            JournalPageHeader(page = page.value)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 1.dp,
                        color = ColorPalette.outline,
                        shape = RoundedCornerShape(size = currentDimensions().S)
                    )
            ) {
                Box(modifier = Modifier.padding(currentDimensions().S)) {
                    if (pageId == note.value.id) {
                        if (page.value.type == PageType.NOTE) {
                            if (!editMode.value) {
                                ViewNote(note.value)
                            } else {
                                EditNote(note.value)
                            }
                        }
                    }
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
