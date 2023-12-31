package com.salach.journalhub.db.models

import androidx.compose.ui.graphics.toArgb
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.salach.journalhub.ui.theme.ColorPalette
import java.time.LocalDate

@Entity
data class Journal(
    @ColumnInfo var title: String,
    @ColumnInfo var subtitle: String,
    @ColumnInfo var icon: Int? = null,
    @ColumnInfo var iconColor: Int = ColorPalette.FrenchGray10.toArgb(),
    @ColumnInfo var backgroundColor: Int = ColorPalette.FrenchGray40.toArgb(),
    @ColumnInfo val createdDate: LocalDate? = null,
    @ColumnInfo var showCreatedDate: Boolean = true,
    @ColumnInfo val editedDate: LocalDate? = null,
    @ColumnInfo var showEditedDate: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
