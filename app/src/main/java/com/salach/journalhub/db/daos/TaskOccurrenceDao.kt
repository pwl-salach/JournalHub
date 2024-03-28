package com.salach.journalhub.db.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.salach.journalhub.db.models.TaskOccurrence
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface TaskOccurrenceDao {
    @Query("SELECT * FROM TaskOccurrence")
    fun getAll(): Flow<List<TaskOccurrence>>

    @Query("SELECT * FROM TaskOccurrence WHERE id = :id")
    fun getById(id: Long): TaskOccurrence

    @Query("SELECT * FROM TaskOccurrence WHERE taskId = :taskId")
    fun getByTaskId(taskId: Long): List<TaskOccurrence>

    @Query("SELECT * FROM TaskOccurrence WHERE taskId = :taskId AND dttm = :date")
    fun getByTaskIdAndDate(taskId: Long, date: LocalDateTime): TaskOccurrence

    @Query("SELECT * FROM TaskOccurrence WHERE taskId = :taskId AND dttm >= :startDate AND dttm <= :endDate")
    fun getByTaskIdAndDateRange(taskId: Long, startDate: LocalDateTime, endDate: LocalDateTime): List<TaskOccurrence>

    @Update
    fun update(taskOccurrence: TaskOccurrence)

    @Update
    fun updateAll(vararg taskOccurrences: TaskOccurrence)

    @Query("DELETE FROM TaskOccurrence")
    suspend fun deleteAll()
}