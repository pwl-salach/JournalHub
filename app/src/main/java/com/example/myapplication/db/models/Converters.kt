package com.example.myapplication.db.models

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime

class Converters {
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }

    @TypeConverter
    fun toLocalDate(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime?): Long? {
        return time?.toNanoOfDay()
    }

    @TypeConverter
    fun toLocalTime(value: Long?): LocalTime? {
        return value?.let { LocalTime.ofNanoOfDay(it) }
    }
}
