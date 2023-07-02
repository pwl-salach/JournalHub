package com.example.myapplication.repositories

import com.example.myapplication.db.daos.GoalDao
import com.example.myapplication.db.models.Goal
import kotlinx.coroutines.flow.Flow

class DashboardRepository(private val goalDao: GoalDao) {
    fun getActiveGoals(): Flow<List<Goal>> {
        return goalDao.getAll()
    }
}