package com.salach.journalhub.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.salach.journalhub.enums.TimeUnit
import java.time.LocalDate
import java.time.LocalTime

@Entity
data class Schedule (
    @ColumnInfo(index = true) val parentId: Int,
    @ColumnInfo val everyNth: Int,
    @ColumnInfo val timeUnit: TimeUnit,
    @ColumnInfo val time: LocalTime,
    @ColumnInfo val startDate: LocalDate,
    @ColumnInfo val endDate: LocalDate? = null,
    @ColumnInfo val calculateUntil: LocalDate? = null,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
) {
    fun getScheduleDescription(): String {
        return when (everyNth) {
            1 -> "Every ${timeUnit.toSingular().lowercase()}"
            2 -> "Every other ${timeUnit.name.lowercase()}"
            else -> "Every $everyNth ${timeUnit.name.lowercase()}"
        }
    }
}
