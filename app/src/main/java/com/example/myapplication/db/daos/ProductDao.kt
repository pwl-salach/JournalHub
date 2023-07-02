package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.db.models.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getAll(): Flow<List<Product>>
}
