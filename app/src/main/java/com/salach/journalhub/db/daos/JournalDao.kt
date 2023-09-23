package com.salach.journalhub.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.salach.journalhub.db.models.Journal
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal WHERE id = :id")
    fun getJournal(id: Int): LiveData<Journal>

    @Query("SELECT * FROM journal")
    fun getAll(): Flow<List<Journal>>

    @Insert
    suspend fun insert(journal: Journal): Long

    @Update
    suspend fun update(journal: Journal)

    @Delete
    suspend fun delete(journal: Journal)

    @Query("DELETE FROM Note")
    suspend fun deleteAll()
}
