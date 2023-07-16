package com.salach.journalhub.repositories

import com.salach.journalhub.db.daos.JournalDao
import com.salach.journalhub.db.models.Journal
import kotlinx.coroutines.flow.Flow

class JournalsRepository(private val journalDao: JournalDao) {

    fun getJournalById(id: Int): Journal{
        return journalDao.getJournal(id)
    }
    fun getJournals(): Flow<List<Journal>>{
        return journalDao.getAll()
    }

    suspend fun insertJournal(journal: Journal) {
        journalDao.insert(journal)
    }

    suspend fun updateJournal(journal: Journal) {
        journalDao.update(journal)
    }

    suspend fun removeJournal(journal: Journal){
        journalDao.delete(journal)
    }
}
