package com.salach.journalhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.IconsGroup
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography

@Composable
fun IconsFrame(name: String, iconsGroup: List<Int>) {
    val dimensions = currentDimensions()
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = ColorPalette.primarySurface2, shape = RoundedCornerShape(size = dimensions.Half))
            .padding(dimensions.XS)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            for(i in 0..1) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensions.XS,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    for(j in 0..1) {
                        Icon(
                            painter = painterResource(id = iconsGroup[i*2 + j]),
                            contentDescription = "",
                            modifier = Modifier.width(dimensions.S).height(dimensions.S)
                        )
                    }
                }
            }
        }
        Text(
            text = name,
            style = currentTypography().L2R
        )
    }
}

@Preview
@Composable
fun PreviewIconsFrame(){
    IconsFrame("Space", IconsGroup.space)
}
