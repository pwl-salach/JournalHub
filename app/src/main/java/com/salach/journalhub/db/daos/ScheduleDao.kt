package com.salach.journalhub.db.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.salach.journalhub.db.models.Schedule
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM Schedule")
    fun getAll(): Flow<List<Schedule>>

    @Query("SELECT * FROM Schedule WHERE id = :id")
    fun getById(id: Long): Schedule

    @Query("SELECT * FROM Schedule WHERE parentId = :parentId")
    fun getByTaskId(parentId: Long): List<Schedule>

    @Update
    fun update(schedule: Schedule)

    @Update
    fun updateAll(vararg schedules: Schedule)

    @Query("DELETE FROM Schedule")
    suspend fun deleteAll()
}