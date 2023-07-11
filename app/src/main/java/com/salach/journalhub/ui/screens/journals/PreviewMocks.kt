package com.salach.journalhub.ui.screens.journals

import androidx.compose.ui.graphics.toArgb
import com.salach.journalhub.R
import com.salach.journalhub.db.daos.JournalDao
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.repositories.JournalsRepository
import com.salach.journalhub.ui.theme.ColorPalette
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate


internal fun provideViewModelForPreview(): JournalsViewModel {
    val mockedRepository = JournalsRepository(MockedJournalDao())
    return JournalsViewModel(mockedRepository)
}

class MockedJournalDao: JournalDao {
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
}
