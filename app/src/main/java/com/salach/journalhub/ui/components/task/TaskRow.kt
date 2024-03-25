package com.salach.journalhub.ui.components.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.R
import com.salach.journalhub.ui.components.basic.IconButton
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography

@Composable
fun TaskRow() {
    val dimensions = currentDimensions()
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.XS)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensions.XL + dimensions.XS)
                .background(
                    color = ColorPalette.primarySurface2,
                    shape = RoundedCornerShape(size = dimensions.Half)
                )
        ) {
            TaskContent()
            TaskControls()
        }
        ScheduleRepresentationRow()
    }
}

@Composable
fun TaskContent(){
    val dimensions = currentDimensions()
    val size = dimensions.L + dimensions.XS
    Column(

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(size)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(size)
                    .height(size)
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = {},
                    modifier = Modifier
                        .width(dimensions.M)
                        .height(dimensions.M)
                )
            }
            Text(
                text = "Task",
                style = currentTypography().B1R,
            )
        }
    }
}

@Composable
fun TaskControls(){
    val dimensions = currentDimensions()
    val size = dimensions.L + dimensions.XS
    Row(
        horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.End),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(size)
    ) {
        IconButton(
            iconId = R.drawable.ic_x,
            description = "Icon 0",
            onClick = {}
        )
        IconButton(
            iconId = R.drawable.ic_sun,
            description = "Icon 0",
            onClick = {}
        )
    }
}

@Composable
fun ScheduleRepresentationRow(){
    val dimensions = currentDimensions()
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.End),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensions.S)
    ) {
        Schedule()
        Notification()
        Repeat()
    }
}

@Composable
fun Schedule(){
    SchedulePart(R.drawable.ic_calendar, "20/10/2024, 21:30")
}

@Composable
fun Notification(){
    SchedulePart(R.drawable.ic_apple, "15 min before")
}

@Composable
fun Repeat(){
    SchedulePart(R.drawable.ic_arrow_back, "Every day")
}

@Composable
fun SchedulePart(icon: Int, text: String){
    val dimensions = currentDimensions()
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensions.XS, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier
                .width(dimensions.S)
                .height(dimensions.S),
            contentScale = ContentScale.Crop
        )
        Text(
            text = text,
            style = currentTypography().B3R,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskRowPreview() {
    TaskRow()
}
