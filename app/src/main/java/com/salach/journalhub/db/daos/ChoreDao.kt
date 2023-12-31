package com.salach.journalhub.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.salach.journalhub.db.models.Chore
import kotlinx.coroutines.flow.Flow

@Dao
interface ChoreDao {
    @Query("SELECT * FROM Chore")
    fun getAll(): Flow<List<Chore>>

//    @Query("SELECT * FROM Chore WHERE noteId = :noteId")
//    fun getAllFromNote(noteId: Int): Flow<List<Chore>>

    @Query("SELECT * FROM Chore WHERE id = :id")
    fun getById(id: Long): LiveData<Chore>

    @Insert
    suspend fun insertAll(vararg chores: Chore)

    @Update
    fun update(chore: Chore)

    @Query("DELETE FROM Chore")
    suspend fun deleteAll()
}
