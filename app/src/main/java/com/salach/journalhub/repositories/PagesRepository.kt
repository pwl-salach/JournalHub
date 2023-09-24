package com.salach.journalhub.repositories

import androidx.lifecycle.LiveData
import com.salach.journalhub.db.daos.ChoreDao
import com.salach.journalhub.db.daos.NoteDao
import com.salach.journalhub.db.daos.PageDao
import com.salach.journalhub.db.models.Chore
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.enums.PageType
import kotlinx.coroutines.flow.Flow

class PagesRepository(private val pageDao: PageDao, private val noteDao: NoteDao, private val choreDao: ChoreDao) {

    fun getAllPages(journalId: Int): Flow<List<Page>> {
        return pageDao.getPageParts(journalId)
    }

    fun<T> getFullRepresentation(partId: Long, type: PageType): LiveData<T>? {
        if (type == PageType.NOTE ){
            @Suppress("UNCHECKED_CAST")
            return noteDao.getById(partId) as LiveData<T>
        } else if (type == PageType.TASK_LIST) {
            @Suppress("UNCHECKED_CAST")
            return choreDao.getById(partId) as LiveData<T>
        }
        return null
    }

    suspend fun<T> insert(page: Page, obj: T, type: PageType){
        val pageId = pageDao.insert(page)
        when (type) {
            PageType.NOTE -> {
                val newNote = obj as Note
                newNote.id = pageId
                noteDao.insertAll(newNote)
            }
            PageType.TASK_LIST -> {
                val newMemo = obj as Chore
                newMemo.id = pageId
                choreDao.insertAll(newMemo)
            }
            else -> {}
        }
    }

    suspend fun<T> update(page: Page, obj: T, type: PageType){
        pageDao.update(page)
        when (type) {
            PageType.NOTE -> {
                val newNote = obj as Note
                noteDao.update(newNote)
            }
            PageType.TASK_LIST -> {
                val newMemo = obj as Chore
                choreDao.update(newMemo)
            }
            else -> {}
        }
    }
}
