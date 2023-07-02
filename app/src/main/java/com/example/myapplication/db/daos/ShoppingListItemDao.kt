package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.db.models.ShoppingListItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ShoppingListItemDao {
    @Query("SELECT * FROM ShoppingListItem")
    fun getAll(): Flow<List<ShoppingListItem>>
}
