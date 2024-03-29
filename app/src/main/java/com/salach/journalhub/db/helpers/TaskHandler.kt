package com.salach.journalhub.db.helpers

import androidx.lifecycle.LiveData
import com.salach.journalhub.db.daos.TaskDao
import com.salach.journalhub.db.models.Task

class TaskHandler(private val taskDao: TaskDao) : PageTypeHandler<Task> {
    override fun cast(obj: PageRepresentation): Task = obj as Task
    override fun getById(partId: Long): LiveData<Task>? {
        TODO("Not yet implemented")
    }

    override suspend fun update(obj: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(obj: Task) = taskDao.insertAll(obj)
}
