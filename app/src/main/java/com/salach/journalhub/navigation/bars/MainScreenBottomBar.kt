package com.salach.journalhub.navigation.bars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.R
import com.salach.journalhub.Screen
import com.salach.journalhub.navigation.graphs.Graph
import com.salach.journalhub.ui.components.SelectableIconButton
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions

@Composable
fun MainScreenBottomBar(
    navController: NavHostController,
    rootController: NavHostController,
    popupOpener: () -> Unit
){
    val items = listOf(
        Screen("dashboard", R.drawable.ic_dashboard, "Dashboard"),
        Screen("journals", R.drawable.ic_notebook, "Journals"),
        Screen("settings", R.drawable.ic_notes, "Settings"),
        Screen("settings", R.drawable.ic_calendar, "Settings")
    )
    val currentDestination = navController.currentBackStackEntryAsState()
    var selectedItem by remember { mutableStateOf(0)}

    LaunchedEffect(currentDestination.value) {
        selectedItem = items.withIndex().firstOrNull { (_, it) ->
            it.route == currentDestination.value?.destination?.route
        }?.index ?: 0
    }

    BottomNavigation(
        backgroundColor = ColorPalette.primarySurface3,
        modifier = Modifier
            .height(currentDimensions().BottomBarHeight)
            .fillMaxWidth()
//            .fillMaxHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(currentDimensions().S)
        ){
            items.forEachIndexed { index, screen ->
                val isSelected = selectedItem == index
                SelectableIconButton(iconId = screen.icon, description = screen.title, isSelected = isSelected) {
                    selectedItem = index
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                }
            }
            if (selectedItem == 1){
                SelectableIconButton(
                    iconId = R.drawable.ic_notebook,
                    description = "CreateJournal",
                    isSelected = true,
                    highlightedColor = ColorPalette.primary,
                    shape = CircleShape
                ) {
                    rootController.navigate(Graph.EDIT_JOURNAL)
                }
            } else {
                SelectableIconButton(
                    iconId = R.drawable.ic_pencil_plus,
                    description = "CreatePage",
                    isSelected = true,
                    highlightedColor = ColorPalette.primary,
                    shape = CircleShape,
                    onClick = popupOpener
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreenBottomBar(){
    val navController = rememberNavController()
    MainScreenBottomBar(navController, navController){}
}