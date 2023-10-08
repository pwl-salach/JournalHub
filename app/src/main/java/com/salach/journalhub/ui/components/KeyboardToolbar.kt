package com.salach.journalhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.R
import com.salach.journalhub.ui.components.basic.IconButton

import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography
import com.salach.journalhub.utils.AnnotatedTextFormatter

@Composable
fun KeyboardToolbar(annotator: MutableState<AnnotatedTextFormatter>) {
    val dimensions = currentDimensions()
    val typography = currentTypography()
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.S, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .shadow(
                elevation = dimensions.Half,
                spotColor = Color(0x36000000),
                ambientColor = Color(0x36000000)
            )
            .background(
                color = ColorPalette.primarySurface3,
                shape = RoundedCornerShape(size = dimensions.Half)
            )
            .fillMaxWidth()
            .height(dimensions.XL + dimensions.XL + dimensions.XL)
            .padding(dimensions.S)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Text options",
                style = typography.T2R
            )
            androidx.compose.material3.IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(dimensions.M)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_x),
                    contentDescription = "CloseToolbox"
                )
            }

        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
            ) {
                IconButton(iconId = R.drawable.ic_bold, description = "") {
                    annotator.value.boldEnabled = !annotator.value.boldEnabled
                }
                IconButton(iconId = R.drawable.ic_italic, description = "") {
                    annotator.value.italicEnabled = !annotator.value.italicEnabled
                }
                IconButton(iconId = R.drawable.ic_underline, description = "") {
                    annotator.value.underlineEnabled = !annotator.value.underlineEnabled
                }
                IconButton(iconId = R.drawable.ic_strikethrough, description = "") {
                    annotator.value.strikethroughEnabled = !annotator.value.strikethroughEnabled
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
            ) {
                IconButton(iconId = R.drawable.ic_h1, description = "") {

                }
                IconButton(iconId = R.drawable.ic_h2, description = "") {

                }
                IconButton(iconId = R.drawable.ic_h3, description = "") {

                }
                IconButton(iconId = R.drawable.ic_pilcrow, description = "") {

                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
// Child views.
        }
    }

}

@Preview
@Composable
fun PreviewKeyboardToolbar(){
    val annotator = remember {mutableStateOf(AnnotatedTextFormatter())}
    KeyboardToolbar(annotator)
}
