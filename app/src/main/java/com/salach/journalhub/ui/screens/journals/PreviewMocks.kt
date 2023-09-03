package com.salach.journalhub.ui.screens.journals

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.MutableLiveData
import com.salach.journalhub.R
import com.salach.journalhub.db.daos.JournalDao
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.repositories.JournalsRepository
import com.salach.journalhub.ui.theme.ColorPalette
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate


fun getJournalsForPreview() : MutableLiveData<List<Journal>> {
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
        ),        Journal("Title", "Test",
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
        ),
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
    return previewData
}

internal fun provideViewModelForPreview(): JournalsViewModel {
    val mockedRepository = JournalsRepository(MockedJournalDao())
    return JournalsViewModel(mockedRepository)
}

class MockedJournalDao: JournalDao {
    override fun getJournal(id: Int): Journal {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flow<List<Journal>> {
        val mockedList = listOf<Journal>(
            Journal("Title", "Test",
                R.drawable.ic_planetscale, ColorPalette.FrenchGray40.toArgb(), ColorPalette.Leaf70.toArgb(),
                LocalDate.of(2012, 5, 10), true, LocalDate.of(2012, 5, 10)
            ),
            Journal("Title", "Test",
                R.drawable.ic_planetscale, ColorPalette.AlertsNeutral50.toArgb(), ColorPalette.Lavender50.toArgb()    ,
                LocalDate.of(2012, 5, 10), false, LocalDate.of(2012, 5, 10)
            )
        )
        val mutableStateFlow = MutableStateFlow(mockedList)
        return mutableStateFlow.asStateFlow()
    }

    override suspend fun insert(journal: Journal): Long {
        return 123
    }

    override suspend fun update(journal: Journal) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(journal: Journal) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}
