package com.salach.journalhub.ui.screens.pages

import androidx.lifecycle.LiveData
import com.salach.journalhub.db.daos.ChoreDao
import com.salach.journalhub.db.daos.NoteDao
import com.salach.journalhub.db.daos.PageDao
import com.salach.journalhub.db.models.Chore
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.repositories.JournalsRepository
import com.salach.journalhub.repositories.PagesRepository
import com.salach.journalhub.ui.screens.journals.MockedJournalDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class MockedPageDao: PageDao {
    override fun getPageParts(noteId: Int): Flow<List<Page>> {
        val mockedList = listOf<Page>()
        val mutableStateFlow = MutableStateFlow(mockedList)
        return mutableStateFlow.asStateFlow()
    }

    override suspend fun insert(note: Page): Long {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(vararg note: Page): List<Long> {
        TODO("Not yet implemented")
    }

    override fun update(page: Page) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

}

class MockedMemoDao: NoteDao{
    override fun getAll(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): LiveData<Note> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(vararg notes: Note) {
        TODO("Not yet implemented")
    }

    override fun update(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

}

class MockedChoreDao: ChoreDao{
    override fun getAll(): Flow<List<Chore>> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): LiveData<Chore> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(vararg chores: Chore) {
        TODO("Not yet implemented")
    }

    override fun update(chore: Chore) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

}

internal fun provideViewModelForPreview(): PagesViewModel {
    val mockedRepository = PagesRepository(MockedPageDao(), MockedMemoDao(), MockedChoreDao())
    val mockedJournalsRepository = JournalsRepository(MockedJournalDao())
    return PagesViewModel(mockedRepository, mockedJournalsRepository)
}
