package com.example.myapplication.ui.screens.dashboard

import com.example.myapplication.R
import com.example.myapplication.db.daos.GoalDao
import com.example.myapplication.db.models.Goal
import com.example.myapplication.repositories.DashboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


internal fun provideViewModelForPreview(): DashboardViewModel {
    val mockedRepository = DashboardRepository(MockedGoalDao())
    return DashboardViewModel(mockedRepository)
}

class MockedGoalDao: GoalDao {
    override fun getAll(): Flow<List<Goal>> {
        val mockedList = listOf<Goal>(
            Goal(R.drawable.ic_launcher_foreground, "Abc", "small test", 0.69f, 0),
            Goal(R.drawable.ic_launcher_foreground, "HGCVH", "small test", 0.21f, 1),
        )
        val mutableStateFlow = MutableStateFlow(mockedList)
        return mutableStateFlow.asStateFlow()
    }

    override suspend fun insert(note: Goal): Long {
        return 123
    }

    override suspend fun insertAll(vararg note: Goal): List<Long> {
        return listOf(123, 456)
    }

    override suspend fun deleteAll() {}
}
