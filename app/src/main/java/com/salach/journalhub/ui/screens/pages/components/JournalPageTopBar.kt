package com.salach.journalhub.ui.screens.pages.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.salach.journalhub.R
import com.salach.journalhub.ui.components.basic.IconButton
import com.salach.journalhub.ui.theme.currentDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalPageTopBar(
    editMode: Boolean,
    backOnClick: () -> Unit,
    modeOnClick: () -> Unit,
    addScheduleOnClick: () -> Unit,
    optionsOnClick: () -> Unit
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(
                iconId = R.drawable.ic_arrow_back,
                description = "Back",
                onClick = backOnClick
            )
        },
        actions = {
            IconButton(
                iconId = if (editMode) R.drawable.ic_check else R.drawable.ic_pencil,
                description = "ReadMode",
                onClick = modeOnClick
            )
            IconButton(
                iconId = R.drawable.ic_calendar_plus,
                description = "AddSchedule",
                onClick = addScheduleOnClick
            )
            IconButton(
                iconId = R.drawable.ic_dots_vertical,
                description = "Options",
                onClick = optionsOnClick
            )
        }
    )
}

@Preview
@Composable
fun PreviewJournalPageTopBar(){
    Column(verticalArrangement = Arrangement.spacedBy(currentDimensions().S)){
        JournalPageTopBar(false, {}, {}, {}, {})
        JournalPageTopBar(true, {}, {}, {}, {})
    }
}
