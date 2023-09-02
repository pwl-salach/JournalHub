package com.salach.journalhub.ui.screens.journals

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.JournalHub
import com.salach.journalhub.R
import com.salach.journalhub.ui.theme.currentDimensions


val LocalViewModel = compositionLocalOf<JournalsViewModel> {
    error("No ViewModel provided")
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JournalsScreen(navController: NavHostController, rootController: NavHostController) {
    val viewModel: JournalsViewModel = LocalViewModel.current
    val viewSwitch = remember {mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(
                    text = "Journals"
                )},
                actions = {
                    IconButton(onClick = { /* Handle action click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search"
                        )
                    }
                    IconButton(onClick = { viewSwitch.value = !viewSwitch.value }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_columns_3),
                            contentDescription = "SwitchView"
                        )
                    }
                    IconButton(onClick = { /* Handle action click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dots_vertical),
                            contentDescription = "Options"
                        )
                    }
                },
            )
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = (currentDimensions().BottomBarHeight)
                )
                .fillMaxSize()
        ) {
            val itemsState by viewModel.journals.observeAsState(emptyList())

            if (itemsState.isEmpty()) {
                NoJournalView(navController)
            } else {
                if (viewSwitch.value) {
                    CarouselJournalView(viewModel.journals, navController, rootController)
                } else {
                    ListJournalsView(viewModel.journals, navController, rootController)
                }
            }
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
        JournalsScreen(navController, navController)
    }
}
