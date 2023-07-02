package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R


@Composable
fun CollapsableColumn(title: String, content: @Composable () -> Unit){
    val isCollapsed = remember { mutableStateOf(false) }

    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .offset(x = 16.dp, y = 0.dp)
                .width(380.dp)
                .height(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                color = Color(0xFF272830),
                textAlign = TextAlign.Center,
                letterSpacing = 0.14.sp,
                modifier = Modifier
                    .offset(x = 0.dp, y = 0.dp)
                    .width(70.dp)
                    .height(16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
//                modifier = Modifier
//                    .offset(x = 340.dp, y = 0.dp)
//                    .width(40.dp)
//                    .height(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tabler_icon_eye_off),
                    contentDescription = "Show/hide",
                    modifier = Modifier
//                        .offset(x = 0.dp, y = 0.dp)
                        .width(16.dp)
                        .height(16.dp)
                        .clickable {
                            isCollapsed.value = !isCollapsed.value
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Show/hide",
                    modifier = Modifier
//                        .offset(x = 24.dp, y = 0.dp)
                        .width(16.dp)
                        .height(16.dp)
                )
            }
        }
        if (!isCollapsed.value){
            content()
        }
    }
}


@Preview
@Composable
fun PreviewCollapsableColumn(){
    CollapsableColumn(title = "Daily goals"){
        Text(text = "Dddd")
    }
}

