package com.salach.journalhub.db.models

import androidx.compose.ui.text.AnnotatedString
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.salach.journalhub.db.helpers.PageRepresentation


@Entity(
    foreignKeys = [ForeignKey(
        entity = Page::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Note(
    @PrimaryKey override var id: Long = 0,
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT) var text: AnnotatedString,
) : PageRepresentation
