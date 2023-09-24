package com.salach.journalhub.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.salach.journalhub.db.models.Page
import kotlinx.coroutines.flow.Flow


@Dao
interface PageDao {
    @Query("SELECT * FROM Page where journalId = :pageId")
    fun getPageParts(pageId: Int): Flow<List<Page>>

    @Insert
    suspend fun insert(page: Page): Long

    @Insert
    suspend fun insertAll(vararg page: Page): List<Long>

    @Update
    fun update(page: Page)

    @Query("DELETE FROM Page")
    suspend fun deleteAll()
}
