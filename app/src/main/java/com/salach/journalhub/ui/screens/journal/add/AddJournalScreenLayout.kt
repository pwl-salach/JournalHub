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
import com.salach.journalhub.ui.components.journal.JournalCover
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions

@Composable
fun AddJournalScreenLayout(journal: MutableState<Journal>, updateTrigger: Boolean = false, content: @Composable () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimensions.S, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(
                start = Dimensions.S,
                top = Dimensions.S,
                end = Dimensions.S,
                bottom = (Dimensions.BottomBarHeight)
            )

    ) {
        Box(
            modifier = Modifier.padding(vertical = Dimensions.S)
        ){
            JournalCover(journal.value, updateTrigger)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimensions.S),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(310.dp)
                .fillMaxWidth()
                .background(
                    color = ColorPalette.primarySurface1,
                    shape = RoundedCornerShape(size = Dimensions.Half)
                )
                .padding(Dimensions.S)

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
