package com.salach.journalhub.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.JournalHub
import com.salach.journalhub.db.models.Schedule
import com.salach.journalhub.enums.TimeUnit
import com.salach.journalhub.ui.components.CollapsableColumn
import com.salach.journalhub.ui.components.CurrentGoals
import com.salach.journalhub.ui.components.MySchedule
import com.salach.journalhub.ui.theme.currentDimensions
import java.time.LocalDate
import java.time.LocalTime


val LocalViewModel = compositionLocalOf<DashboardViewModel> {
    error("No ViewModel provided")
}


@Composable
fun DashboardScreen(navController: NavHostController){
    val viewModel: DashboardViewModel = LocalViewModel.current
    val previewData = MutableLiveData<List<Schedule>>()
    previewData.value = listOf(
        Schedule(0, 1, TimeUnit.DAYS, LocalTime.now(), LocalDate.now()),
        Schedule(0, 1, TimeUnit.DAYS, LocalTime.now(), LocalDate.now()),
        Schedule(0, 1, TimeUnit.DAYS, LocalTime.now(), LocalDate.now()),
    )
    Column(
        modifier = Modifier.padding(start = currentDimensions().S, end = currentDimensions().S)
    ) {
        CollapsableColumn(title = "Daily Goal") {
            CurrentGoals(goals = viewModel.goals)
        }
        CollapsableColumn(title = "My Schedule") {
            MySchedule(previewData)
        }
    }
}

@Composable
fun ProvideDashboardViewModel(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val viewModel: DashboardViewModel = viewModel(
        factory = DashboardViewModelFactory(
            (context.applicationContext as JournalHub).dashboardRepository
        )
    )
    CompositionLocalProvider(LocalViewModel provides viewModel) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardScreen(){
    val navController = rememberNavController()
    CompositionLocalProvider(LocalViewModel provides provideViewModelForPreview()) {
        DashboardScreen(navController = navController)
    }
}