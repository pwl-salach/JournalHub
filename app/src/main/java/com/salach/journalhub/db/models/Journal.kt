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
    @ColumnInfo val icon: Int? = null,
    @ColumnInfo val iconColor: Int = ColorPalette.FrenchGray10.toArgb(),
    @ColumnInfo var backgroundColor: Int = ColorPalette.FrenchGray40.toArgb(),
    @ColumnInfo val createdDate: LocalDate? = null,
    @ColumnInfo val editedDate: LocalDate? = null,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
