package com.salach.journalhub.ui.screens.journal.add

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.navigation.bars.AddJournalBottomBar
import com.salach.journalhub.navigation.graphs.AddJournalNavGraph
import com.salach.journalhub.navigation.graphs.Route
import com.salach.journalhub.navigation.graphs.TabNavGraph
import java.time.LocalDate


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJournalScreen(rootController: NavHostController) {
    val journal = remember { mutableStateOf(
        Journal("", "", createdDate = LocalDate.now(), editedDate = LocalDate.now())
    )}
    val nestedNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            AddJournalBottomBar(rootController, nestedNavController){
                print(journal)
            }
        }
    ) {
        AddJournalNavGraph(journal, nestedNavController)
    }
//}
//    Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
//        when (currentStage?.destination?.route) {
//            FlowStage.Init.route -> AddJournalInitScreen(journal.value) {
//                // Update the object state or perform any necessary validation
//                // Navigate to the next stage
//                navController.navigate(flowStages[currentIndex + 1].route)
//            }
//            FlowStage.PickCoverColor.route -> PickJournalColor(journal.value) {
//                navController.navigate(flowStages[currentIndex + 1].route)
//            }
//        }
//    }
}
