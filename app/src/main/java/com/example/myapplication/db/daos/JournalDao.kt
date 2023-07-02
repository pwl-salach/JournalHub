package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.db.models.Journal
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal")
    fun getAll(): Flow<List<Journal>>

    @Insert
    suspend fun insert( journal: Journal): Long
}
