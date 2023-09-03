package com.salach.journalhub.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salach.journalhub.db.models.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE id = :id")
    suspend fun getById(id: Long): Note

    @Insert
    suspend fun insertAll(vararg notes: Note)

    @Query("DELETE FROM Note")
    suspend fun deleteAll()
}
