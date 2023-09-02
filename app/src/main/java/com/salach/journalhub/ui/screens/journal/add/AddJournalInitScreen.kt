package com.salach.journalhub.ui.screens.journal.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.components.InputLine
import com.salach.journalhub.ui.components.NamedCheckbox
import com.salach.journalhub.ui.theme.currentDimensions
import com.salach.journalhub.ui.theme.currentTypography
import java.time.LocalDate


@Composable
fun AddJournalInitScreen(journal: MutableState<Journal>){
    val updateTrigger = remember { mutableStateOf(false) }
    AddJournalScreenLayout(journal, updateTrigger.value) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(currentDimensions().S)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(currentDimensions().S, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ){
                Text(
                    text = "Add text on the cover.",
                    style = currentTypography().T2B,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(currentDimensions().S, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    InputLine(
                        value = journal.value.title,
                        textStyle = currentTypography().T2R,
                        placeholder = "Title...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics { contentDescription = "TitleInput" },
                        onValueChange = {
                            journal.value.title = it
                            updateTrigger.value = !updateTrigger.value
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    )
                    InputLine(
                        value = journal.value.subtitle,
                        textStyle = currentTypography().T2R,
                        placeholder = "Subtitle...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics { contentDescription = "SubtitleInput" },
                        onValueChange = {
                            journal.value.subtitle = it
                            updateTrigger.value = !updateTrigger.value
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(currentDimensions().S, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                NamedCheckbox(
                    label = "Show creation date.",
                    checked = journal.value.showCreatedDate,
                    onCheckedChange = {
                        journal.value.showCreatedDate = !journal.value.showCreatedDate
                        updateTrigger.value = !updateTrigger.value
                    }
                )
                NamedCheckbox(
                    label = "Show “last edited” date.",
                    checked = journal.value.showEditedDate,
                    onCheckedChange = {
                        journal.value.showEditedDate = !journal.value.showEditedDate
                        updateTrigger.value = !updateTrigger.value
                    }
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewAddJournalScreen(){
    val mutableJournal = mutableStateOf(
        Journal("","", createdDate = LocalDate.now(), editedDate = LocalDate.now())
    )
    AddJournalInitScreen(mutableJournal)
}
