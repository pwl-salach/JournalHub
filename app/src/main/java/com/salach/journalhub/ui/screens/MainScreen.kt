package com.salach.journalhub.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.navigation.bars.MainScreenBottomBar
import com.salach.journalhub.navigation.graphs.TabNavGraph


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(rootController: NavHostController = rememberNavController()) {
    val nestedNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            MainScreenBottomBar(nestedNavController, rootController)
        },
    ) {
        TabNavGraph(nestedNavController, rootController)
    }
}
