package com.salach.journalhub.ui.screens.journal.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.components.ColorPicker
import com.salach.journalhub.ui.theme.currentDimensions
import java.time.LocalDate

@Composable
fun PickJournalColor(journal: MutableState<Journal>) {
//    val pickedColor = remember { mutableStateOf(journal.value.backgroundColor) }
    val updateTrigger = remember { mutableStateOf(false) }

    AddJournalScreenLayout(journal, updateTrigger.value) {
        Column(
            verticalArrangement = Arrangement.spacedBy(currentDimensions().S, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            ColorPicker(
                prompt = "Select cover colour.",
                initialColor = journal.value.backgroundColor,
                onColorPicked = {
//                    pickedColor.value = it
                    journal.value.backgroundColor = it
                    updateTrigger.value = !updateTrigger.value
                }
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewPickJournalColor(){
    val mutableState = mutableStateOf(Journal(
        "New Title",
        "New Subtitle",
        createdDate = LocalDate.now()
    ))
    PickJournalColor(
            mutableState
    )
}
