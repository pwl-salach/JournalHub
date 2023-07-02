package com.example.myapplication.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = NotePart::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Memo(
    @PrimaryKey var id: Long = 0,
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT) val text: String,
)
