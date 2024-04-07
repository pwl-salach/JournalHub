package com.salach.journalhub.ui.screens.pages.task

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.salach.journalhub.db.models.Task

@Composable
fun ViewTaskList(taskList: List<Task>) {
    Column {
        taskList.forEach { task ->
            Text(text = task.shortDesc)
        }
    }
}