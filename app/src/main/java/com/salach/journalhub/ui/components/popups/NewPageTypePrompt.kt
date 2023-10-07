package com.salach.journalhub.ui.components.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.enums.PageType
import com.salach.journalhub.enums.getPageTypeIconId
import com.salach.journalhub.enums.getPageTypeString
import com.salach.journalhub.navigation.graphs.Graph
import com.salach.journalhub.ui.components.PageTypeCard
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography
import kotlinx.coroutines.launch
import java.util.Locale


fun getCreateButtonDescription(nameOfType: String): String {
    val parts = nameOfType.split(" ")
    parts.forEach {part ->
        part.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
    return "Create${parts.joinToString("")}"
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewPageTypePrompt(
    sheetState: ModalBottomSheetState,
    navController: NavHostController
) {
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
                    .padding(dimensions.S)
                    .padding(bottom = dimensions.BottomBarHeight)
            ) {
                Text(
                    text = "Create New",
                    style = typography.T2R
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(dimensions.S, Alignment.Top),
//                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start,
                ){
                    items(PageType.values().toList().windowed(4,4,true)){ groupedScope ->
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            items(groupedScope){
                                val typeName = getPageTypeString(it)
                                PageTypeCard(
                                    iconId = getPageTypeIconId(it),
                                    text = typeName,
                                    description = getCreateButtonDescription(typeName)
                                ) {
                                    navController.navigate("${Graph.NOTE_PAGE}?newPageType=${it.name}")
                                }
                            }
                        }
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
