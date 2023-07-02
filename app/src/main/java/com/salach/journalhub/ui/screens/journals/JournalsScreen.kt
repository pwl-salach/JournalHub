package com.salach.journalhub.ui.screens.journals

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.JournalHub


val LocalViewModel = compositionLocalOf<JournalsViewModel> {
    error("No ViewModel provided")
}

@Composable
fun JournalsScreen(navController: NavHostController) {
    val viewModel: JournalsViewModel = LocalViewModel.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        val itemsState by viewModel.journals.observeAsState(emptyList())

        if (itemsState.isEmpty()){
            NoJournalView(navController, viewModel)
        } else {
            CarouselJournalView(viewModel.journals)
        }
    }
}



@Composable
fun ProvideJournalViewModel(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val viewModel: JournalsViewModel = viewModel(
        factory = JournalsViewModelFactory(
            (context.applicationContext as JournalHub).journalsRepository
        )
    )
    CompositionLocalProvider(LocalViewModel provides viewModel) {
        content()
    }
}

@Preview
@Composable
fun PreviewJournalsScreen(){
    val navController = rememberNavController()
    CompositionLocalProvider(LocalViewModel provides provideViewModelForPreview()) {
        JournalsScreen(navController = navController)
    }
}
