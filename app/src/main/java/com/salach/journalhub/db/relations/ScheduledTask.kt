package com.salach.journalhub.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.salach.journalhub.db.helpers.PageRepresentation
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.db.models.Schedule
import com.salach.journalhub.db.models.Task


data class ScheduledTask(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val schedules: List<Schedule>
)