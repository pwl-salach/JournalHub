package com.salach.journalhub.ui.screens.journal.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.theme.Dimensions
import com.salach.journalhub.ui.theme.Typography
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJournalInitScreen(journal: MutableState<Journal>){
    val title = remember { mutableStateOf(journal.value.title) }
    val subtitle = remember { mutableStateOf(journal.value.subtitle) }
    val showCreationDate = remember { mutableStateOf(journal.value.showCreatedDate) }
    val showLastEditedDate = remember { mutableStateOf(journal.value.showEditedDate) }

    val updateTrigger = remember { mutableStateOf(false) }
    AddJournalScreenLayout(journal, updateTrigger.value) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimensions.s, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(Dimensions.s)
                .fillMaxWidth()
        ) {
            Text(
                text = "Add text on the cover.",
                style = Typography.T2B,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimensions.s, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                TextField(
                    value = title.value,
                    onValueChange = {
                        title.value = it
                        journal.value.title = it
                        updateTrigger.value = !updateTrigger.value
                    },
                    placeholder = { Text("Title...", color = Color.Gray) },
                    textStyle = Typography.T2R,
                    modifier = Modifier.height(52.dp)
                )
                TextField(
                    value = subtitle.value,
                    onValueChange = {
                        subtitle.value = it
                        journal.value.subtitle = it
                        updateTrigger.value = !updateTrigger.value
                    },
                    placeholder = { Text("Subtitle...", color = Color.Gray) },
                    textStyle = Typography.T2R,
                    modifier = Modifier.height(52.dp)
    //                        color = Color(0xFF929292),
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimensions.s, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimensions.xs, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = showCreationDate.value,
                        onCheckedChange = {
                            showCreationDate.value = !showCreationDate.value
                            journal.value.showCreatedDate = showCreationDate.value
                            updateTrigger.value = !updateTrigger.value
                        },
                        modifier = Modifier.height(Dimensions.m)
                    )
                    Text(
                        text = "Show creation date.",
                        style = Typography.T2R
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimensions.xs, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = showLastEditedDate.value,
                        onCheckedChange = {
                            showLastEditedDate.value = !showLastEditedDate.value
                            journal.value.showEditedDate = showLastEditedDate.value
                            updateTrigger.value = !updateTrigger.value
                        },
                        modifier = Modifier.height(Dimensions.m)
                    )
                    Text(
                        text = "Show “last edited” date.",
                        style = Typography.T2R
                    )
                }
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
