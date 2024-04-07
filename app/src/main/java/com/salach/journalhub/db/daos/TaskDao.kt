package com.salach.journalhub.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.salach.journalhub.db.models.Task
import com.salach.journalhub.db.relations.TasksList

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAll(): LiveData<List<Task>>

    @Insert
    suspend fun insertAll(vararg tasks: Task)

    @Update
    fun update(task: Task)

    @Query("DELETE FROM Task")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM Page WHERE id = :id")
    fun getById(id: Long): LiveData<TasksList>
}
