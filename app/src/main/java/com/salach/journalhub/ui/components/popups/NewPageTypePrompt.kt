package com.salach.journalhub.ui.components.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.R
import com.salach.journalhub.enums.PageType
import com.salach.journalhub.navigation.graphs.Graph
import com.salach.journalhub.ui.components.PageTypeCard
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewPageTypePrompt(sheetState: ModalBottomSheetState, navController: NavHostController) {
    val dimensions = currentDimensions()
    val typography = currentTypography()
    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(size = currentDimensions().XS),
        sheetBackgroundColor = ColorPalette.primarySurface5,
        sheetContent = {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensions.S, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .shadow(
                        elevation = dimensions.XS,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .fillMaxWidth()
                    .background(
                        color = ColorPalette.primarySurface3,
                        shape = RoundedCornerShape(size = dimensions.XS)
                    )
                    .padding(bottom = dimensions.BottomBarHeight)
            ) {
                Text(
                    text = "Create New",
                    style = typography.T2R
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // single item
                    PageTypeCard(
                        iconId = R.drawable.ic_file,
                        text = "Note",
                        description = "createNote"
                    ) {
                        navController.navigate("${Graph.NOTE_PAGE}?newPageType=${PageType.NOTE.name}")
                    }
                    PageTypeCard(
                        iconId = R.drawable.ic_checkup_list,
                        text = "Task list",
                        description = "createTaskList"
                    ) {
                        navController.navigate("${Graph.NOTE_PAGE}?newPageType=${PageType.CHORE.name}")
                    }
                    PageTypeCard(
                        iconId = R.drawable.ic_shopping_cart,
                        text = "Shopping list",
                        description = "createShoppingList"
                    ) {

                    }
                    PageTypeCard(
                        iconId = R.drawable.ic_target_arrow,
                        text = "Goal",
                        description = "createGoal"
                    ) {
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PageTypeCard(
                        iconId = R.drawable.ic_run,
                        text = "Activity plan",
                        description = "createActivityPlan"
                    ) {

                    }
                }
            }
        },
        sheetState = sheetState
    ){}
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PreviewNewPageTypePrompt(){
    NewPageTypePrompt(
        rememberModalBottomSheetState(ModalBottomSheetValue.Expanded),
        rememberNavController()
    )
}
