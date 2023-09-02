package com.salach.journalhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.salach.journalhub.R
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions


@Composable
fun  NavBarIconButton(
    icon: Int,
    description: String,
    isSelected: Boolean,
    highlightedColor: Color = ColorPalette.tertiary,
    shape: Shape = RoundedCornerShape(size = currentDimensions().Half),
    onClick: () -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.width(currentDimensions().XL).height(currentDimensions().XL)
            .background(
                if (isSelected) highlightedColor else ColorPalette.primarySurface3,
                shape = shape
            )
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = description,
            tint = if (isSelected) Color.White else Color.Black,
            modifier = Modifier
                .height(currentDimensions().L)
                .width(currentDimensions().L)
                .clickable { onClick() }
        )
    }
}

@Preview
@Composable
fun PreviewNavBarIconButton(){
    NavBarIconButton(R.drawable.ic_dashboard, "Test", true){}
}
