package com.example.myapplication

import android.app.Application
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.repositories.DashboardRepository
import com.example.myapplication.repositories.JournalsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getInstance(this, applicationScope) }
    val dashboardRepository by lazy { DashboardRepository(database.goalDao) }
    val journalsRepository by lazy { JournalsRepository(database.journalDao)}
}