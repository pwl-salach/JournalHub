package com.salach.journalhub.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.salach.journalhub.enums.PageType
import java.time.LocalDate


@Entity(
    foreignKeys = [ForeignKey(
        entity = Journal::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("journalId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Page(
    @ColumnInfo(index = true) val journalId: Int,
    @ColumnInfo val title: String,
    @ColumnInfo var editedDate: LocalDate,
    @ColumnInfo val type: PageType,
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo val position: Int? = null,
)
