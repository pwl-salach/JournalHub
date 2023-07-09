package com.salach.journalhub.ui.screens.journal.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.components.BigJournal
import com.salach.journalhub.ui.components.ColorPicker
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions
import java.time.LocalDate

@Composable
fun PickJournalColor(journal: MutableState<Journal>) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight().padding(
            start = 16.dp, top = 16.dp, end = 16.dp, bottom = (Dimensions.bottomBarHeight + 16.dp)
        )
    ) {
        Box(
            modifier = Modifier.padding(vertical = 16.dp)
        ){
            BigJournal(journal.value)
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .background(
                    color = ColorPalette.FrenchGray20,
                    shape = RoundedCornerShape(size = 4.dp)
                )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                ColorPicker{
                    journal.value.backgroundColor = it
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewPickJournalColor(){
//    PickJournalColor(
//        Journal(
//            "New Title",
//            "New Subtitle",
//            createdDate = LocalDate.now()
//        )
//    ){}
//}