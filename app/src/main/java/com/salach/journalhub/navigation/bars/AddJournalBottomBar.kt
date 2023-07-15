package com.salach.journalhub.navigation.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.R
import com.salach.journalhub.navigation.graphs.Route
import com.salach.journalhub.ui.components.NavBarIconButton
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions

@Composable
fun AddJournalBottomBar(
    rootController: NavHostController, navController: NavHostController,
    finishedFlowCallback: () -> Unit
){
    val flowStages = listOf(
        FlowStage.Init,
        FlowStage.PickCoverColor,
        FlowStage.PickIcon,
        FlowStage.PickIconColor
    )
    val currentStage by navController.currentBackStackEntryAsState()
    val currentIndex = flowStages.indexOfFirst { it.route == currentStage?.destination?.route }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimensions.bottomBarHeight)
            .background(color = ColorPalette.primarySurface3)
            .padding(horizontal = 16.dp)
    ) {
        NavBarIconButton(
            icon = R.drawable.ic_x, isSelected = false
        ) {
            rootController.popBackStack()
        }
        Text(
            text = "Step ${currentIndex + 1}/${flowStages.size}",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            textAlign = TextAlign.Right,
            letterSpacing = 0.14.sp
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (currentIndex > 0) {
                NavBarIconButton(
                    icon = R.drawable.ic_chevron_left, isSelected = false
                ) {
                    navController.popBackStack()
                }
            }
            if (currentIndex + 1 < flowStages.size) {
                NavBarIconButton(
                    icon = R.drawable.ic_chevron_right, isSelected = false
                ) {
                    navController.navigate(flowStages[currentIndex + 1].route)
                }
            } else {
                NavBarIconButton(
                    icon = R.drawable.ic_arrow_back, isSelected = false
                ) {
                    finishedFlowCallback()
                    rootController.popBackStack()
                }
            }
        }
    }
}

sealed class FlowStage(val route: String) {
    object Init : FlowStage(Route.AddJournalInit.link)
    object PickCoverColor : FlowStage(Route.AddJournalPickColor.link)
    object PickIcon : FlowStage(Route.AddJournalPickIcon.link)
    object PickIconColor : FlowStage(Route.AddJournalPickIconColor.link)
}

@Preview
@Composable
fun PreviewAddJournalBottomBar(){
    val navController = rememberNavController()
    AddJournalBottomBar(navController, navController){}
}

