package com.salach.journalhub.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.navigation.bars.MainScreenBottomBar
import com.salach.journalhub.navigation.graphs.TabNavGraph


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(rootController: NavHostController = rememberNavController()) {
    val nestedNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            MainScreenBottomBar(nestedNavController, rootController)
        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {
//                    rootController.navigate(Graph.NEW_JOURNAL) {
//                        // Add any desired navigation options
//                    }
//                },
//            ) {
//                Icon(Icons.Default.Add, contentDescription = "Add Journal")
//            }
//        }
    ) {
        TabNavGraph(navController = nestedNavController)
    }
}
