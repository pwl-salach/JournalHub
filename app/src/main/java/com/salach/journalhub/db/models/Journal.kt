package com.salach.journalhub.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Journal(
    @ColumnInfo val title: String,
    @ColumnInfo val subtitle: String,
    @ColumnInfo val icon: Int,
    @ColumnInfo val iconColor: Long,
    @ColumnInfo val backgroundColor: Long,
    @ColumnInfo val createdDate: LocalDate,
    @ColumnInfo val editedDate: LocalDate?,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
