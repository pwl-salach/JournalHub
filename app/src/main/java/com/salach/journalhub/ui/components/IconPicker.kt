package com.salach.journalhub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salach.journalhub.R
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions

@Composable
fun IconPicker(onIconPicked: (Int) -> Unit){
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimensions.s, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = ColorPalette.SurfaceLight, shape = RoundedCornerShape(size = Dimensions.half))
            .padding(Dimensions.s)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimensions.l, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Select cover image.",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.14.sp,
                )
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimensions.s, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Space",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(400),
                        letterSpacing = 0.14.sp,
                    )
                )
                IconsRow(icons = listOf(
                    R.drawable.ic_sun,
                    R.drawable.ic_moon_stars,
//                    R.drawable.ic_planetscale,
                    R.drawable.ic_planet_off
                ), onIconClicked = onIconPicked )
            }
        }
    }
}


@Preview
@Composable
fun PreviewIconPicker(){
    IconPicker(){}
}

@Composable
fun IconsRow(icons: List<Int>, onIconClicked: (Int) -> Unit){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Dimensions.s, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        items(icons){
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier
                    .width(Dimensions.l)
                    .height(Dimensions.l)
                    .clickable { onIconClicked(it) }
            )
        }
    }
}

@Preview
@Composable
fun PreviewIconsRow(){
    IconsRow(listOf(R.drawable.ic_apple, R.drawable.ic_baguette)){}
}
