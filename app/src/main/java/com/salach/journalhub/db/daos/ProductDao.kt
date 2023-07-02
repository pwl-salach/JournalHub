package com.salach.journalhub.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.salach.journalhub.db.models.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getAll(): Flow<List<Product>>
}
