package com.salach.journalhub.ui.screens.pages

import androidx.lifecycle.LiveData
import com.salach.journalhub.db.daos.JournalDao
import com.salach.journalhub.db.daos.TaskDao
import com.salach.journalhub.db.daos.NoteDao
import com.salach.journalhub.db.daos.PageDao
import com.salach.journalhub.db.daos.ScheduleDao
import com.salach.journalhub.db.daos.TaskOccurrenceDao
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.db.models.Task
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.db.models.Schedule
import com.salach.journalhub.db.models.TaskOccurrence
import com.salach.journalhub.repositories.JournalsRepository
import com.salach.journalhub.repositories.PagesRepository
import com.salach.journalhub.ui.screens.journals.MockedJournalDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime


class MockedJournalDao: JournalDao {
    override fun getJournal(id: Int): LiveData<Journal> {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flow<List<Journal>> {
        val mockedList = listOf<Journal>()
        val mutableStateFlow = MutableStateFlow(mockedList)
        return mutableStateFlow.asStateFlow()
    }

    override suspend fun insert(journal: Journal): Long {
        TODO("Not yet implemented")
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

class MockedPageDao: PageDao {
    override fun getPageById(pageId: Long): Flow<Page> {
        TODO("Not yet implemented")
    }

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

    override suspend fun update(page: Page) {
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

    override suspend fun update(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

}

class MockedTaskDao: TaskDao{
    override fun getAll(): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): LiveData<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(vararg tasks: Task) {
        TODO("Not yet implemented")
    }

    override fun update(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}


class MockedScheduleDao: ScheduleDao{
    override fun getAll(): Flow<List<Schedule>> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): Schedule {
        TODO("Not yet implemented")
    }

    override fun getByTaskId(pageId: Long): List<Schedule> {
        TODO("Not yet implemented")
    }

    override fun update(schedule: Schedule) {
        TODO("Not yet implemented")
    }

    override fun updateAll(vararg schedules: Schedule) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}

class MockedTaskOccurrenceDao: TaskOccurrenceDao {
    override fun getAll(): Flow<List<TaskOccurrence>> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): TaskOccurrence {
        TODO("Not yet implemented")
    }

    override fun getByTaskId(taskId: Long): List<TaskOccurrence> {
        TODO("Not yet implemented")
    }

    override fun getByTaskIdAndDate(taskId: Long, date: LocalDateTime): TaskOccurrence {
        TODO("Not yet implemented")
    }

    override fun getByTaskIdAndDateRange(
        taskId: Long,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): List<TaskOccurrence> {
        TODO("Not yet implemented")
    }

    override fun update(taskOccurrence: TaskOccurrence) {
        TODO("Not yet implemented")
    }

    override fun updateAll(vararg taskOccurrences: TaskOccurrence) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

}

internal fun provideViewModelForPreview(): PagesViewModel {
    val mockedRepository = PagesRepository(MockedScheduleDao(), MockedPageDao(), MockedMemoDao(), MockedTaskDao(), MockedTaskOccurrenceDao())
    val mockedJournalsRepository = JournalsRepository(MockedJournalDao())
    return PagesViewModel(mockedRepository, mockedJournalsRepository)
}
