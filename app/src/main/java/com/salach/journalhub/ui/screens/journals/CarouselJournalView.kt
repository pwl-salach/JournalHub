package com.salach.journalhub.ui.screens.journals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.salach.journalhub.R
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.ui.components.BigJournal
import com.salach.journalhub.ui.theme.ColorPalette
import com.salach.journalhub.ui.theme.Dimensions
import java.time.LocalDate


@Composable
fun CarouselJournalView(journals: LiveData<List<Journal>>){
    val itemsState by journals.observeAsState(emptyList())
    val selectedIndex = remember {mutableStateOf(0)}
    Column {
        BigJournal(journals.value!![selectedIndex.value])
        LazyRow(
            contentPadding = PaddingValues(horizontal = Dimensions.s),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.s)
        ){
            itemsIndexed(itemsState) { index, journal ->
//                if (index != 0) {
//                    Spacer(modifier = Modifier.width(Dimensions.s)) // Adjust the spacing as needed
//                }
                Box(modifier = Modifier
                    .width(50.dp)
                    .aspectRatio(1f)

                ) {
                    BigJournal(journal)
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
