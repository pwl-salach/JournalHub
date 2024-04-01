package com.salach.journalhub.ui.screens.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
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
import com.salach.journalhub.db.helpers.PageRepresentation
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.enums.PageType
import com.salach.journalhub.ui.screens.journal.view.LocalPagesViewModel
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
    val viewModel: PagesViewModel = LocalPagesViewModel.current
    val pagination = Pagination(journalId)
    val editMode = remember { mutableStateOf(false) }

    val journalPages by viewModel.getPages(journalId).observeAsState(emptyList())
    val page = remember {
        mutableStateOf(
            Page(journalId, "", LocalDate.now(), type = newPageType)
        )
    }
    val representation: MutableState<PageRepresentation> = remember {
        mutableStateOf(Note(id = -1, text = AnnotatedString("")))
    }

    if (pageId != -1L) {
        pagination.findPage(journalPages, pageId!!)
        viewModel.getPage(pageId).observeAsState().value?.let { page.value = it }
        viewModel.loadPageRepresentation<PageRepresentation>(pageId, PageType.NOTE)
            ?.observeAsState()?.value?.let { representation.value = it }
    } else {
        editMode.value = true
    }
    Scaffold(
        topBar = {
            JournalPageTopBar(
                editMode = editMode.value,
                backOnClick = { navController.popBackStack() },
                modeOnClick = {
                    if (editMode.value) {
                        viewModel.savePage(page.value, representation.value, newPageType)
                    }
                    editMode.value = !editMode.value
                },
                addScheduleOnClick = {},
                optionsOnClick = {}
            )
        },
        bottomBar = {
            if (!editMode.value) {
                pagination.Draw(navController, journalPages.count())
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
                        shape = RoundedCornerShape(topEnd = currentDimensions().S)
                    )
            ) {
                Box(
                    modifier = Modifier.padding(currentDimensions().S)
                ) {
                    if (pageId == representation.value.id) {
                        if (page.value.type == PageType.NOTE) {
                            if (!editMode.value) {
                                ViewNote(representation.value as Note)
                            } else {
                                EditNote(representation.value as Note)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProvidePagesViewModel(content: @Composable () -> Unit) {
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
fun PreviewViewJournalPage() {
    val nav = rememberNavController()
    CompositionLocalProvider(LocalPagesViewModel provides provideViewModelForPreview()) {
        ViewJournalPage(0, 0, PageType.NOTE, nav)
    }
}
