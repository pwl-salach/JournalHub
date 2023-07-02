package com.example.myapplication.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.example.myapplication.MyApplication
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.db.models.Schedule
import com.example.myapplication.enums.TimeUnit
import com.example.myapplication.ui.components.CollapsableColumn
import com.example.myapplication.ui.components.CurrentGoals
import com.example.myapplication.ui.components.MySchedule
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
        Schedule(0, TimeUnit.DAYS, LocalDate.now(), null, LocalTime.now(), null),
        Schedule(0, TimeUnit.DAYS, LocalDate.now(), null, LocalTime.now(), null),
        Schedule(0, TimeUnit.DAYS, LocalDate.now(), null, LocalTime.now(), null),
    )
    Column() {
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
            (context.applicationContext as MyApplication).dashboardRepository
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