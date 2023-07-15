package com.salach.journalhub.ui.screens.journal.add

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.components.IconsFrame
import com.salach.journalhub.ui.theme.Dimensions
import com.salach.journalhub.ui.theme.IconsGroup
import com.salach.journalhub.ui.theme.Typography

@Composable
fun PickJournalIcon(journal: MutableState<Journal>) {
    val groupsPerRow = 4
    val updateTrigger = remember { mutableStateOf(false) }
    val selectedGroup = remember { mutableStateOf("") }

    AddJournalScreenLayout(journal, updateTrigger.value) {
        Text(
            text = "Select cover image.",
            style = Typography.T2R
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = if (selectedGroup.value == "") "Categories" else "Categories / ${selectedGroup.value}",
                style = Typography.T2R,
                modifier = Modifier.height(Dimensions.m)
            )
            if (selectedGroup.value != "") {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = "back",
                    modifier = Modifier
                        .height(Dimensions.m)
                        .clickable {
                            selectedGroup.value = ""
                        }
                )
            }
        }
        if (selectedGroup.value == "") {
            LazyColumn {
//            itemsIndexed(IconsGroup.grouped.toList()){index, (name, iconsGroup) ->
                items(
                    IconsGroup.grouped.toList().windowed(groupsPerRow, groupsPerRow, true)
                ) { rowItems ->
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(
                            Dimensions.xs,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.Top
                    ) {
                        items(rowItems) { (name, iconsGroup) ->
                            Box(modifier = Modifier.clickable {
                                selectedGroup.value = name
                            }) {
                                IconsFrame(name = name, iconsGroup = iconsGroup)
                            }
                        }
                    }
                }
            }
        } else {
            LazyColumn {
                IconsGroup.grouped[selectedGroup.value]?.let {
                    items(it.windowed(6,6,true)){ rowItems ->
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(Dimensions.s, Alignment.Start),
                            modifier = Modifier.fillMaxWidth()
                        ){
                            items(rowItems){it ->
                                Icon(
                                    painter = painterResource(id = it),
                                    contentDescription = null,
                                    modifier = Modifier.height(40.dp).width(40.dp)
                                        .clickable {
                                            journal.value.icon = it
                                            updateTrigger.value = !updateTrigger.value
                                        }
                                )
                            }
                        }
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
    PickJournalIcon(mutableStateOf(Journal("","")))
}
