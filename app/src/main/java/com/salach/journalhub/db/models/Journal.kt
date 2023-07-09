package com.salach.journalhub.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.salach.journalhub.ui.theme.ColorPalette
import java.time.LocalDate

@Entity
data class Journal(
    @ColumnInfo var title: String,
    @ColumnInfo var subtitle: String,
    @ColumnInfo val icon: Int? = null,
    @ColumnInfo val iconColor: Long = ColorPalette.FrenchGray80.value.toLong(),
    @ColumnInfo var backgroundColor: Long = ColorPalette.FrenchGray30.value.toLong(),
    @ColumnInfo val createdDate: LocalDate? = null,
    @ColumnInfo val editedDate: LocalDate? = null,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
