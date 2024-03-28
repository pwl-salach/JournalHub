package com.salach.journalhub.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.salach.journalhub.db.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAll(): Flow<List<Task>>

//    @Query("SELECT * FROM Chore WHERE noteId = :noteId")
//    fun getAllFromNote(noteId: Int): Flow<List<Chore>>

    @Query("SELECT * FROM Task WHERE id = :id")
    fun getById(id: Long): LiveData<Task>

    @Insert
    suspend fun insertAll(vararg tasks: Task)

    @Update
    fun update(task: Task)

    @Query("DELETE FROM Task")
    suspend fun deleteAll()
}
