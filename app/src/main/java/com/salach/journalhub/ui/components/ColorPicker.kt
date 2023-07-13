package com.salach.journalhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Typography


@Composable
fun ColorPicker(onColorPicked: (Int) -> Unit) {
    val colors = listOf(
        ColorPalette.Lavender70, ColorPalette.Lavender40, ColorPalette.Lavender20,
        ColorPalette.Leaf70, ColorPalette.Leaf40, ColorPalette.Leaf20,
        ColorPalette.FrenchGray70, ColorPalette.FrenchGray40, ColorPalette.FrenchGray20,
    )
    val gradientBrushes = listOf(
        Brush.verticalGradient(listOf(Color.Red, Color.Yellow)),
        Brush.horizontalGradient(listOf(Color.Green, Color.Blue)),
        Brush.linearGradient(listOf(Color.Magenta, Color.Cyan)),Brush.verticalGradient(listOf(Color.Red, Color.Yellow)),
        Brush.horizontalGradient(listOf(Color.Green, Color.Blue)),
        Brush.linearGradient(listOf(Color.Magenta, Color.Cyan)),
        Brush.verticalGradient(listOf(Color.Red, Color.Yellow)),
        Brush.horizontalGradient(listOf(Color.Green, Color.Blue)),
        Brush.linearGradient(listOf(Color.Magenta, Color.Cyan)),
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = ColorPalette.SurfaceLight, shape = RoundedCornerShape(size = 4.dp))
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Select cover colour.",
                style = Typography.L1B
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Solid colours",
                style = Typography.L1R
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(colors) { color ->
                    Box(modifier = Modifier
                        .size(32.dp)
                        .background(color, shape = CircleShape)
                        .clickable {
                            onColorPicked(color.toArgb())
                        }
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Gradients",
                style = Typography.L1R
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(gradientBrushes) { brush ->
                    Box(modifier = Modifier
                        .size(32.dp)
                        .background(brush, shape = CircleShape)
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
