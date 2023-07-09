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
import androidx.compose.ui.unit.dp
import com.salach.journalhub.R
import com.salach.journalhub.ui.theme.ColorPalette


@Composable
fun NavBarIconButton(
    icon: Int,
    isSelected: Boolean,
    highlightedColor: Color = ColorPalette.Lavender30,
    shape: Shape = RoundedCornerShape(size = 4.dp),
    onClick: () -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.width(48.dp).height(48.dp)
            .background(
                if (isSelected) highlightedColor else ColorPalette.PrimarySurface3,
                shape = shape
            )
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier
                .height(32.dp)
                .width(32.dp)
                .clickable { onClick() }
        )
    }
}

@Preview
@Composable
fun PreviewNavBarIconButton(){
    NavBarIconButton(R.drawable.ic_dashboard, true,){}
}
