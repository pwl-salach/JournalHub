package com.salach.journalhub.db.helpers

import androidx.lifecycle.LiveData
import com.salach.journalhub.db.daos.NoteDao
import com.salach.journalhub.db.models.Note

class NoteHandler(private val noteDao: NoteDao) : PageTypeHandler<Note> {
    override fun cast(obj: PageRepresentation): Note = obj as Note
    override fun getById(partId: Long): LiveData<Note> {
        return noteDao.getById(partId)
    }

    override suspend fun update(obj: Note) {
        noteDao.update(obj)
    }

    override suspend fun insert(obj: Note) = noteDao.insertAll(obj)
}
