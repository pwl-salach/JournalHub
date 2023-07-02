package com.example.myapplication.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.enums.TimeUnit
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity
data class Schedule (
    @ColumnInfo val everyNth: Int,
    @ColumnInfo val timeUnit: TimeUnit,
    @ColumnInfo val startDttm: LocalDate,
    @ColumnInfo val endDttm: LocalDate?,
    @ColumnInfo val time: LocalTime,
    @PrimaryKey(autoGenerate = true) val id: Int?
)
