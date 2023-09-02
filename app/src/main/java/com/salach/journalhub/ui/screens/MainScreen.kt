package com.salach.journalhub.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.navigation.bars.MainScreenBottomBar
import com.salach.journalhub.navigation.graphs.TabNavGraph
import com.salach.journalhub.ui.components.popups.NewPageTypePrompt
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(rootController: NavHostController = rememberNavController()) {
    val nestedNavController = rememberNavController()
    val popupState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            MainScreenBottomBar(nestedNavController, rootController){
                coroutineScope.launch {
                    popupState.show()
                }
            }
        },
    ) {
        NewPageTypePrompt(popupState, rootController)
        TabNavGraph(nestedNavController, rootController)
    }
}
