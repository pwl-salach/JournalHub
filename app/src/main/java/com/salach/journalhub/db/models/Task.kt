package com.salach.journalhub.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = Page::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("pageId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Task(
    @ColumnInfo(index = true) val pageId: Long,
    @ColumnInfo val shortDesc: String,
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
)
