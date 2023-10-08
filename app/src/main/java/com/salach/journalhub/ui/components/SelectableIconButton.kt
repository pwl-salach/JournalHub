package com.salach.journalhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.salach.journalhub.R
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions


@Composable
fun SelectableIconButton(
    iconId: Int,
    description: String,
    isSelected: Boolean,
    highlightedColor: Color = ColorPalette.tertiary,
    shape: Shape = RoundedCornerShape(size = currentDimensions().Half),
    iconSize: Dp? = null, borderSize: Dp? = null,
    onClick: () -> Unit
){
    val dimensions = currentDimensions()
    val iconSize = iconSize ?: dimensions.L
    val totalSize = iconSize + (borderSize ?: dimensions.S)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(totalSize)
            .background(
                if (isSelected) highlightedColor else ColorPalette.primarySurface3,
                shape = shape
            )
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = description,
            tint = if (isSelected) Color.White else Color.Black,
            modifier = Modifier
                .size(iconSize)
                .clickable { onClick() }
        )
    }
}

@Preview
@Composable
fun PreviewNavBarIconButton(){
    Column(
        verticalArrangement = Arrangement.spacedBy(currentDimensions().M)
    ){
        SelectableIconButton(R.drawable.ic_dashboard, "Test", true){}
        SelectableIconButton(R.drawable.ic_barbell, "Test", false){}
    }
}
