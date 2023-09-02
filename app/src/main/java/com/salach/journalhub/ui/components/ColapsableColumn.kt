package com.salach.journalhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.R
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography


@Composable
fun CollapsableColumn(title: String, content: @Composable () -> Unit){
    val isCollapsed = remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(currentDimensions().S, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(currentDimensions().L)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                style = currentTypography().T3B,
                modifier = Modifier
                    .offset(x = 0.dp, y = 0.dp)
                    .height(currentDimensions().L)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(currentDimensions().S, Alignment.Start),
                verticalAlignment = Alignment.Top,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Show/hide",
                    modifier = Modifier
                        .width(currentDimensions().L)
                        .height(currentDimensions().L)
                        .padding(currentDimensions().Half)
                )
                Image(
                    painter = painterResource(id = R.drawable.tabler_icon_eye_off),
                    contentDescription = "Show/hide",
                    modifier = Modifier
//                        .offset(x = 0.dp, y = 0.dp)
                        .width(currentDimensions().L)
                        .height(currentDimensions().L)
                        .padding(currentDimensions().Half)
                        .clickable {
                            isCollapsed.value = !isCollapsed.value
                        }
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

