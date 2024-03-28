package com.salach.journalhub.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(foreignKeys = [
    ForeignKey(
        entity = Task::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("taskId"),
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Schedule::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("scheduleId"),
        onDelete = ForeignKey.CASCADE
    )
])
data class TaskOccurrence(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val taskId: Int,
    @ColumnInfo(index = true) val scheduleId: Int,
    @ColumnInfo val dttm: LocalDateTime,
    @ColumnInfo val isDone: Boolean
)
