package com.salach.journalhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions
import com.salach.journalhub.ui.theme.Typography


@Composable
fun ColorPicker(onColorPicked: (Int) -> Unit) {
    val selectedItemId = remember { mutableStateOf(-1) }

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimensions.S, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                color = ColorPalette.SurfaceLight,
                shape = RoundedCornerShape(size = Dimensions.Half)
            )
            .padding(Dimensions.S)
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimensions.L, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Select cover colour.",
                style = Typography.L1B
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimensions.S, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Pastel",
                style = Typography.L1R
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Dimensions.S, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(ColorPalette.Pastels) { index, color ->
                    SelectableColor(
                        color = color,
                        isSelected = index == selectedItemId.value,
                        onItemSelected = {
                            onColorPicked(color.toArgb())
                            selectedItemId.value = index
                        }
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimensions.S, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Neutral",
                style = Typography.L1R
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Dimensions.S, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(ColorPalette.Neutrals) { index, color ->
                    val offsetIndex = 1000 + index
                    SelectableColor(
                        color = color,
                        isSelected = offsetIndex == selectedItemId.value,
                        onItemSelected = {
                            onColorPicked(color.toArgb())
                            selectedItemId.value = offsetIndex
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewColorPicker(){
    ColorPicker {
        print(it)
    }
}

@Composable
fun SelectableColor(color: Color, isSelected: Boolean, onItemSelected: (Int) -> Unit){
    Box(
        modifier = Modifier
            .size(Dimensions.L)
            .clip(shape = CircleShape)
            .clickable {
                onItemSelected(color.toArgb())
            }
            .drawBehind {
                val outerRadius = Dimensions.L.toPx() / 2
                val innerRadius = Dimensions.S.toPx() / 2
                val filledRadius = if (isSelected) innerRadius else outerRadius
                val outerRingWidth = Dimensions.XS.toPx() + Dimensions.Half.toPx()
                drawCircle(
                    color = color,
                    radius = filledRadius,
                )
                if (isSelected) {
                    drawCircle(
                        color = color,
                        radius = outerRadius,
                        style = Stroke(width = outerRingWidth)
                    )
                }
            }
    )
}

@Preview
@Composable
fun PreviewSelectableColor(){
    Row(horizontalArrangement = Arrangement.spacedBy(Dimensions.S)) {
        SelectableColor(ColorPalette.Lavender50, false) {}
        SelectableColor(ColorPalette.Lavender50, true) {}
    }
}
