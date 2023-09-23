package com.salach.journalhub.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = Page::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Note(
    @PrimaryKey var id: Long = 0,
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT) var text: String,
)
