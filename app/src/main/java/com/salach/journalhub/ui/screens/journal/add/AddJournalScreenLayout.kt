package com.salach.journalhub.ui.screens.journal.add

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.components.BigJournal
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions

@Composable
fun AddJournalScreenLayout(journal: MutableState<Journal>, updateTrigger: Boolean = false, content: @Composable () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = (Dimensions.bottomBarHeight + 16.dp)
            )

    ) {
        Box(
            modifier = Modifier.padding(vertical = 16.dp)
        ){
            BigJournal(journal.value, updateTrigger)
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .background(
                    color = ColorPalette.primarySurface1,
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)

        ) {
            content()
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun PreviewAddJournalScreenLayout(){
    AddJournalScreenLayout(mutableStateOf(Journal("",""))){
        Text(text = "TEST")
    }
}
