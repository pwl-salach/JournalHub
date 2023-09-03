package com.salach.journalhub.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.salach.journalhub.db.models.Page
import kotlinx.coroutines.flow.Flow


@Dao
interface PageDao {
    @Query("SELECT * FROM Page where journalId = :noteId")
    fun getNoteParts(noteId: Int): Flow<List<Page>>

    @Insert
    suspend fun insert(note: Page): Long

    @Insert
    suspend fun insertAll(vararg note: Page): List<Long>

    @Query("DELETE FROM Page")
    suspend fun deleteAll()
}
