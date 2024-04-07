package com.salach.journalhub.ui.screens.pages.task

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.salach.journalhub.db.models.Task
import com.salach.journalhub.ui.components.task.TaskRow

@Composable
fun ViewTaskList(taskList: List<Task>) {
    Column {
        taskList.forEach { task ->
            TaskRow(task)
        }
    }
}