package com.salach.journalhub.repositories

import com.salach.journalhub.db.daos.ChoreDao
import com.salach.journalhub.db.daos.NoteDao
import com.salach.journalhub.db.daos.PageDao
import com.salach.journalhub.db.models.Chore
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.enums.PageType
import kotlinx.coroutines.flow.Flow

class PagesRepository(private val pageDao: PageDao, private val noteDao: NoteDao, private val choreDao: ChoreDao) {

    fun getAllPages(noteId: Int): Flow<List<Page>> {
        return pageDao.getNoteParts(noteId)
    }

    suspend fun<T> getFullRepresentation(partId: Long, type: PageType): T? {
        if (type == PageType.NOTE ){
            @Suppress("UNCHECKED_CAST")
            return noteDao.getById(partId) as T
        } else if (type == PageType.CHORE) {
            @Suppress("UNCHECKED_CAST")
            return choreDao.getById(partId) as T
        }
        return null
    }

    suspend fun<T> insert(noteId: Int, obj: T, type: PageType){
        val newPart = Page(noteId, type)
        val partId = pageDao.insert(newPart)
        when (type) {
            PageType.NOTE -> {
                val newNote = obj as Note
                newNote.id = partId
                noteDao.insertAll(newNote)
            }
            PageType.CHORE -> {
                val newMemo = obj as Chore
                newMemo.id = partId
                choreDao.insertAll(newMemo)
            }
        }
    }


}
