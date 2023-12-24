package com.salach.journalhub.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.salach.journalhub.db.models.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE id = :id")
    fun getById(id: Long): LiveData<Note>

    @Insert
    suspend fun insertAll(vararg notes: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM Note")
    suspend fun deleteAll()
}
