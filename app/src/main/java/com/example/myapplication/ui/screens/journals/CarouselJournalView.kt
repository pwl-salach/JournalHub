package com.example.myapplication.ui.screens.journals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.db.models.Journal
import com.example.myapplication.db.models.Schedule
import com.example.myapplication.enums.TimeUnit
import com.example.myapplication.ui.components.BigJournal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


@Composable
fun CarouselJournalView(journals: LiveData<List<Journal>>){
    val itemsState by journals.observeAsState(emptyList())
    val selectedIndex = remember {mutableStateOf(0)}
    Column {
        BigJournal(journals.value!![selectedIndex.value])
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            itemsIndexed(itemsState) { index, journal ->
//                if (index != 0) {
//                    Spacer(modifier = Modifier.width(16.dp)) // Adjust the spacing as needed
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
            R.drawable.ic_planetscale, 0x000000DC, 0xFFCCC2DC,
            LocalDate.of(2012, 5, 10), LocalDate.of(2012, 5, 10)
        ),
        Journal("Title", "Test",
            R.drawable.ic_planetscale, 0x000000DC, 0xFFCCC2DC,
            LocalDate.of(2012, 5, 10), LocalDate.of(2012, 5, 10)
        ),
        Journal("Title", "Test",
            R.drawable.ic_planetscale, 0x000000DC, 0xFFCCC2DC,
            LocalDate.of(2012, 5, 10), LocalDate.of(2012, 5, 10)
        )
    )
    CarouselJournalView(previewData)
}
