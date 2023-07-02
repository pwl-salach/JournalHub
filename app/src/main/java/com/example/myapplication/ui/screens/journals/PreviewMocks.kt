package com.example.myapplication.ui.screens.journals

import com.example.myapplication.R
import com.example.myapplication.db.daos.JournalDao
import com.example.myapplication.db.models.Journal
import com.example.myapplication.repositories.JournalsRepository
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
                R.drawable.ic_planetscale, 0x000000DC, 0xFFCCC2DC,
                LocalDate.of(2012, 5, 10), LocalDate.of(2012, 5, 10)
            ),
            Journal("Title", "Test",
                R.drawable.ic_planetscale, 0x000000DC, 0xFFCCC2DC,
                LocalDate.of(2012, 5, 10), LocalDate.of(2012, 5, 10)
            )
        )
        val mutableStateFlow = MutableStateFlow(mockedList)
        return mutableStateFlow.asStateFlow()
    }

    override suspend fun insert(journal: Journal): Long {
        return 123
    }
}
