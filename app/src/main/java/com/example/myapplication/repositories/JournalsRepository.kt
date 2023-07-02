package com.example.myapplication.repositories

import com.example.myapplication.db.daos.JournalDao
import com.example.myapplication.db.models.Journal
import kotlinx.coroutines.flow.Flow

class JournalsRepository(private val journalDao: JournalDao) {
    fun getJournals(): Flow<List<Journal>>{
        return journalDao.getAll()
    }

    suspend fun insertJournal(journal: Journal) {
        journalDao.insert(journal)
    }
}