package com.salach.journalhub.ui.screens.pages

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
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
import com.salach.journalhub.db.models.NotePart
import com.salach.journalhub.enums.NotePartType
import com.salach.journalhub.ui.screens.pages.components.JournalPageBottomBar
import com.salach.journalhub.ui.screens.pages.components.JournalPageHeader
import com.salach.journalhub.ui.screens.pages.components.JournalPageTopBar


val LocalViewModel = compositionLocalOf<PagesViewModel> {
    error("No ViewModel provided")
}

@Composable
fun ViewJournalPage(
    journalId: Int,
    pageId: Long?,
    newPageType: NotePartType,
    navController: NavHostController
) {
    val editMode = remember { mutableStateOf(false) }

    val viewModel: PagesViewModel = LocalViewModel.current
    val itemsState by viewModel.pages.observeAsState(emptyList())
    val currentPage by lazy {
        if (pageId != -1L){
            viewModel.pages.value?.find{ it.id == pageId }
        } else {
            editMode.value = true
            NotePart(noteId =  journalId, type = newPageType)
        }
    }
    Scaffold(
        topBar = {
            JournalPageTopBar(
                editMode = editMode.value,
                backOnClick = { navController.popBackStack() },
                modeOnClick = { editMode.value = !editMode.value },
                addScheduleOnClick = {},
                optionsOnClick = {}
            )
        },
        bottomBar = {
            if (!editMode.value){
                JournalPageBottomBar(0, itemsState.size)
            }
        }
    ) {
        currentPage?.let { page -> JournalPageHeader(notePart = page, paddingValues = it) }
    }
}

@Composable
fun ProvidePagesViewModel(content: @Composable () -> Unit){
    val context = LocalContext.current
    val viewModel: PagesViewModel = viewModel(
        factory = PagesViewModelFactory(
            (context.applicationContext as JournalHub).pagesRepository
        )
    )
    CompositionLocalProvider(LocalViewModel provides viewModel) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewViewJournalPage(){
    val nav = rememberNavController()
    CompositionLocalProvider(LocalViewModel provides provideViewModelForPreview()) {
        ViewJournalPage(0,0, NotePartType.MEMO, nav)
    }
}
