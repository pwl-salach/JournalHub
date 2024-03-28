package com.salach.journalhub.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(
        entity = ShoppingList::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("shoppingListId"),
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Product::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("productId"),
        onDelete = ForeignKey.CASCADE
    )
])
data class ShoppingListItem(
    @PrimaryKey val id: Int,
    @ColumnInfo(index = true) val shoppingListId: Int,
    @ColumnInfo(index = true) val productId: Int,
    @ColumnInfo val quantity: Int,
    @ColumnInfo val unit: String
)
