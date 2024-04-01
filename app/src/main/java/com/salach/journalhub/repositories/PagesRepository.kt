package com.salach.journalhub.repositories

import androidx.lifecycle.LiveData
import com.salach.journalhub.db.helpers.PageRepresentation
import com.salach.journalhub.db.daos.TaskDao
import com.salach.journalhub.db.daos.NoteDao
import com.salach.journalhub.db.daos.PageDao
import com.salach.journalhub.db.daos.ScheduleDao
import com.salach.journalhub.db.daos.TaskOccurrenceDao
import com.salach.journalhub.db.helpers.NoteHandler
import com.salach.journalhub.db.helpers.PageTypeHandler
import com.salach.journalhub.db.helpers.TaskHandler
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.enums.PageType
import kotlinx.coroutines.flow.Flow


class PagesRepository(
    scheduleDao: ScheduleDao,
    private val pageDao: PageDao,
    noteDao: NoteDao,
    taskDao: TaskDao,
    taskOccurrenceDao: TaskOccurrenceDao
) {
    private val handlers = mapOf(
        PageType.NOTE to NoteHandler(noteDao),
        PageType.TASK_LIST to TaskHandler(taskDao)
    )

    fun getPage(pageId: Long): Flow<Page> {
        return pageDao.getPageById(pageId)
    }

    fun getAllPages(journalId: Int): Flow<List<Page>> {
        return pageDao.getPageParts(journalId)
    }

    fun<T: PageRepresentation> getFullRepresentation(partId: Long, type: PageType): LiveData<T>? {
        val handler = handlers[type] as PageTypeHandler<T>
        return handler.getById(partId)
    }

    suspend fun<T : PageRepresentation> insert(page: Page, obj: T, type: PageType){
        val pageId = pageDao.insert(page)
        obj.id = pageId
        val handler = handlers[type] as PageTypeHandler<T>
        handler.insert(obj)
    }

    suspend fun<T: PageRepresentation> update(page: Page, obj: T, type: PageType){
        pageDao.update(page)
        val handler = handlers[type] as PageTypeHandler<T>
        handler.update(obj)
    }
}
