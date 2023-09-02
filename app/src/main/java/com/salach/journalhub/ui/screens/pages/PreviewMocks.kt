package com.salach.journalhub.ui.screens.pages

import com.salach.journalhub.R
import com.salach.journalhub.db.daos.ChoreDao
import com.salach.journalhub.db.daos.MemoDao
import com.salach.journalhub.db.daos.NotePartDao
import com.salach.journalhub.db.models.Chore
import com.salach.journalhub.db.models.Goal
import com.salach.journalhub.db.models.Memo
import com.salach.journalhub.db.models.NotePart
import com.salach.journalhub.repositories.PagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class MockedNotePartDao: NotePartDao {
    override fun getNoteParts(noteId: Int): Flow<List<NotePart>> {
        val mockedList = listOf<NotePart>()
        val mutableStateFlow = MutableStateFlow(mockedList)
        return mutableStateFlow.asStateFlow()
    }

    override suspend fun insert(note: NotePart): Long {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(vararg note: NotePart): List<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

}

class MockedMemoDao: MemoDao{
    override fun getAll(): Flow<List<Memo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): Memo {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(vararg notes: Memo) {
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

    override suspend fun getById(id: Long): Chore {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(vararg chores: Chore) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

}

internal fun provideViewModelForPreview(): PagesViewModel {
    val mockedRepository = PagesRepository(MockedNotePartDao(), MockedMemoDao(), MockedChoreDao())
    return PagesViewModel(mockedRepository)
}
