package com.salach.journalhub.ui.screens.journals

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.navigation.graphs.Graph
import com.salach.journalhub.ui.components.journal.BigJournalCover
import com.salach.journalhub.ui.components.journal.SmallJournalCover
import com.salach.journalhub.ui.theme.currentDimensions


@Composable
fun CarouselJournalView(
    journals: LiveData<List<Journal>>,
    navController: NavHostController,
    rootController: NavHostController
){
    val itemsState by journals.observeAsState(emptyList())
    val selectedIndex = rememberSaveable {mutableStateOf(0)}
    val viewModel: JournalsViewModel = LocalViewModel.current

    Column(
        verticalArrangement = Arrangement.spacedBy(currentDimensions().L),
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = currentDimensions().L)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            BigJournalCover(
                journals.value!![selectedIndex.value],
                onShowClicked = {},
                onAddClicked = {},
                onEditClicked = {
                    rootController.navigate(
                        "${Graph.NEW_JOURNAL}?journalId=${journals.value!![selectedIndex.value].id}"
                    )
                },
                onRemoveClicked = {
                    viewModel.removeJournal(journals.value!![selectedIndex.value])
                    selectedIndex.value = 0
                }
            )
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = currentDimensions().S),
            horizontalArrangement = Arrangement.spacedBy(currentDimensions().S)
        ){
            itemsIndexed(itemsState) { index, journal ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(60.dp)
                        .height(80.dp)
                        .clickable {
                            selectedIndex.value = index
                        }
                        .border(
                            if (selectedIndex.value == index) currentDimensions().Quarter else Dp.Unspecified,
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(size = currentDimensions().XS)
                        )
                ){
                    SmallJournalCover(journal)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCarouselJournalView(){
    val nav = rememberNavController()
    CompositionLocalProvider(LocalViewModel provides provideViewModelForPreview()) {
        CarouselJournalView(getJournalsForPreview(), nav, nav)
    }
}
