package com.salach.journalhub.ui.screens.journals

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.components.BigJournalCover
import com.salach.journalhub.ui.components.JournalCover
import com.salach.journalhub.ui.components.SmallJournalCover
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions
import java.time.LocalDate


@Composable
fun CarouselJournalView(journals: LiveData<List<Journal>>){
    val itemsState by journals.observeAsState(emptyList())
    val selectedIndex = remember {mutableStateOf(0)}
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimensions.L),
        modifier = Modifier.fillMaxHeight().padding(top = Dimensions.L)
    ){
        BigJournalCover(journals.value!![selectedIndex.value])
        LazyRow(
            contentPadding = PaddingValues(horizontal = Dimensions.S),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.S)
        ){
            itemsIndexed(itemsState) { index, journal ->
                Box(
                    modifier = Modifier.clickable {
                        selectedIndex.value = index
                    }
                ){
                    SmallJournalCover(journal)
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewCarouselJournalView(){
    val previewData = MutableLiveData<List<Journal>>()
    previewData.value = listOf(
        Journal("Title", "Test",
            R.drawable.ic_planetscale, ColorPalette.AlertsNeutral50.toArgb(), ColorPalette.Lavender50.toArgb()    ,
            LocalDate.of(2012, 5, 10), true, LocalDate.of(2012, 5, 10)
        ),
        Journal("Title", "Test",
            R.drawable.ic_planetscale, ColorPalette.FrenchGray03.toArgb(), ColorPalette.FrenchGray30.toArgb(),
            LocalDate.of(2012, 5, 10), false, LocalDate.of(2012, 5, 10)
        ),
        Journal("Title", "Test",
            R.drawable.ic_planetscale, ColorPalette.Leaf50.toArgb(), ColorPalette.Leaf80.toArgb(),
            LocalDate.of(2012, 5, 10), true, LocalDate.of(2012, 5, 10)
        )
    )
    CarouselJournalView(previewData)
}
