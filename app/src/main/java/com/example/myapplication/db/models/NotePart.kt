package com.example.myapplication.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.myapplication.enums.NotePartType


@Entity(
    foreignKeys = [ForeignKey(
        entity = Note::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("noteId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class NotePart(
    @ColumnInfo(index = true) val noteId: Int,
    @ColumnInfo val type: NotePartType,
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo val position: Int? = null,
)
