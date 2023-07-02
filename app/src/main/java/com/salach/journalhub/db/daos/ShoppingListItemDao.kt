package com.salach.journalhub.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.salach.journalhub.db.models.ShoppingListItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ShoppingListItemDao {
    @Query("SELECT * FROM ShoppingListItem")
    fun getAll(): Flow<List<ShoppingListItem>>
}
