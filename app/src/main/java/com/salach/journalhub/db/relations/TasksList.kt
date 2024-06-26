package com.salach.journalhub.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.salach.journalhub.db.helpers.PageRepresentation
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.db.models.Task


data class TasksList(
    @Embedded val page: Page,
    @Relation(
        entity = Task::class,
        parentColumn = "id",
        entityColumn = "pageId"
    )
    val tasks: List<ScheduledTask>
) : PageRepresentation {
    override var id: Long
        get() = page.id!!
        set(value) {}
}