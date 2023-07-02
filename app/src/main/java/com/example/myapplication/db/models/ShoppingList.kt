package com.example.myapplication.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingList(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
)
