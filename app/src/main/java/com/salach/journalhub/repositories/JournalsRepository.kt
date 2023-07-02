package com.salach.journalhub.repositories

import com.salach.journalhub.db.daos.JournalDao
import com.salach.journalhub.db.models.Journal
import kotlinx.coroutines.flow.Flow

class JournalsRepository(private val journalDao: JournalDao) {
    fun getJournals(): Flow<List<Journal>>{
        return journalDao.getAll()
    }

    suspend fun insertJournal(journal: Journal) {
        journalDao.insert(journal)
    }
}