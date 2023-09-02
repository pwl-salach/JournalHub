package com.salach.journalhub

import android.app.Application
import com.salach.journalhub.db.AppDatabase
import com.salach.journalhub.repositories.DashboardRepository
import com.salach.journalhub.repositories.JournalsRepository
import com.salach.journalhub.repositories.PagesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class JournalHub : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getInstance(this, applicationScope) }
    val dashboardRepository by lazy { DashboardRepository(database.goalDao) }
    val journalsRepository by lazy { JournalsRepository(database.journalDao) }
    val pagesRepository by lazy {
        PagesRepository(
            database.notePartDao,
            database.memoDao,
            database.choreDao
        ) }
}