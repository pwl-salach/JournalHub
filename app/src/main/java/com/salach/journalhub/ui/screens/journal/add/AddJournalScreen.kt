package com.salach.journalhub.ui.screens.journal.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salach.journalhub.R
import com.salach.journalhub.ui.components.BigJournal
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions


@Composable
fun AddJournalScreen(){
    var showCreationDate = remember { mutableStateOf(false) }
    var showLastEditedDate = remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        Box(
            modifier = Modifier.padding(vertical = 16.dp)
        ){
            BigJournal()
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(vertical = Dimensions.bottomBarHeight)

//                .offset(x = 16.dp, y = 524.dp)
//                .width(380.dp)
//                .height(294.dp)
                .background(
                    color = ColorPalette.FrenchGray20,
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .offset(x = 16.dp, y = 16.dp)
//                    .width(348.dp)
//                    .height(96.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Add text on the cover.",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
//                    modifier = Modifier
//                        .offset(x = 0.dp, y = 32.dp)
//                        .width(348.dp)
//                        .height(64.dp)
                ) {
                    Text(
                        text = "Title...",
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        color = Color(0xFF464646),
                        letterSpacing = 0.22.sp
                    )
                    Text(
                        text = "Subtitle...",
                        fontSize = 22.sp,
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        color = Color(0xFF929292),
                        letterSpacing = 0.22.sp
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(checked = showCreationDate.value, onCheckedChange = {
                            showCreationDate.value = !showCreationDate.value
                        })
                        Text(
                            text = "Show creation date.",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto)),
                            letterSpacing = 0.14.sp
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(checked = showLastEditedDate.value, onCheckedChange = {
                            showLastEditedDate.value = !showLastEditedDate.value
                        })
                        Text(
                            text = "Show “last edited” date.",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto)),
                            letterSpacing = 0.14.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddJournalScreen(){
    AddJournalScreen()
}
