package com.salach.journalhub.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.salach.journalhub.db.helpers.PageRepresentation


@Entity(
    foreignKeys = [ForeignKey(
        entity = Page::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("pageId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Task(
    @PrimaryKey override var id: Long = 0,
    @ColumnInfo(index = true) val pageId: Long,
    @ColumnInfo val shortDesc: String,
) : PageRepresentation
