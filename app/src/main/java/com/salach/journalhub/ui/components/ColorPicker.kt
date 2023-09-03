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
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography


@Composable
fun ColorPicker(prompt: String, initialColor: Int, onColorPicked: (Int) -> Unit) {
    val selectedItemId = remember { mutableStateOf(-1) }
    val dimensions = currentDimensions()
    val typography = currentTypography()
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.S, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                color = ColorPalette.SurfaceLight,
                shape = RoundedCornerShape(size = dimensions.Half)
            )
            .padding(dimensions.S)
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensions.L, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = prompt,
                style = typography.L1B
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensions.S, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Pastel",
                style = typography.L1R
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(dimensions.S, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(ColorPalette.Pastels) { index, color ->
                    SelectableColor(
                        color = color,
                        isSelected = index == selectedItemId.value || isInitiallySelected(color.toArgb(), initialColor, selectedItemId.value),
                        onItemSelected = {
                            onColorPicked(color.toArgb())
                            selectedItemId.value = index
                        }
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensions.S, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Neutral",
                style = typography.L1R
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(dimensions.S, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(ColorPalette.Neutrals) { index, color ->
                    val offsetIndex = 1000 + index
                    SelectableColor(
                        color = color,
                        isSelected = offsetIndex == selectedItemId.value || isInitiallySelected(color.toArgb(), initialColor, selectedItemId.value),
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

fun isInitiallySelected(color: Int, initialColor: Int, selectedItemId: Int): Boolean{
    return selectedItemId == -1 && initialColor == color
}

@Preview
@Composable
fun PreviewColorPicker(){
    ColorPicker("Select cover colour.", ColorPalette.Pastels[2].toArgb()) {
        print(it)
    }
}

@Composable
fun SelectableColor(color: Color, isSelected: Boolean, onItemSelected: (Int) -> Unit){
    val dimensions = currentDimensions()
    Box(
        modifier = Modifier
            .size(dimensions.L)
            .clip(shape = CircleShape)
            .clickable {
                onItemSelected(color.toArgb())
            }
            .drawBehind {
                val outerRadius = dimensions.L.toPx() / 2
                val innerRadius = dimensions.S.toPx() / 2
                val filledRadius = if (isSelected) innerRadius else outerRadius
                val outerRingWidth = dimensions.XS.toPx() + dimensions.Half.toPx()
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
    Row(horizontalArrangement = Arrangement.spacedBy(currentDimensions().S)) {
        SelectableColor(ColorPalette.Lavender50, false) {}
        SelectableColor(ColorPalette.Lavender50, true) {}
    }
}
