package com.salach.journalhub.db.helpers

import androidx.lifecycle.LiveData
import com.salach.journalhub.db.daos.TaskDao
import com.salach.journalhub.db.relations.TasksList

class TaskHandler(
    private val taskDao: TaskDao
) : PageTypeHandler<TasksList> {
    override fun cast(obj: PageRepresentation): TasksList = obj as TasksList
    override suspend fun update(obj: TasksList) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(obj: TasksList) {
        TODO("Not yet implemented")
    }

    override fun getById(partId: Long): LiveData<TasksList>? {
        return taskDao.getById(partId)
    }
}
