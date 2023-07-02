package com.salach.journalhub.repositories

import com.salach.journalhub.db.daos.GoalDao
import com.salach.journalhub.db.models.Goal
import kotlinx.coroutines.flow.Flow

class DashboardRepository(private val goalDao: GoalDao) {
    fun getActiveGoals(): Flow<List<Goal>> {
        return goalDao.getAll()
    }
}