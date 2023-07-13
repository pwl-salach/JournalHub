package com.salach.journalhub.ui.screens.journal.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.components.IconsFrame
import com.salach.journalhub.ui.theme.IconsGroup
import com.salach.journalhub.ui.theme.Typography

@Composable
fun PickJournalIconsGroup(journal: MutableState<Journal>) {
    val groupsPerRow = 4
    val updateTrigger = remember { mutableStateOf(false) }

    AddJournalScreenLayout(journal, updateTrigger.value) {
        Text(
            text = "Select cover image.",
            style = Typography.T2R
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)){
            Text(
                text = "Categories",
                style = Typography.T2R,
            )
        }
        LazyColumn {
//            itemsIndexed(IconsGroup.grouped.toList()){index, (name, iconsGroup) ->
            items(IconsGroup.grouped.toList().windowed(groupsPerRow, groupsPerRow, true)) { rowItems ->
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.Top
                ){
                    items(rowItems){(name, iconsGroup) ->
                        IconsFrame(name = name, iconsGroup = iconsGroup)
                    }
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun PreviewPickJournalIcon(){
    PickJournalIconsGroup(mutableStateOf(Journal("","")))
}
