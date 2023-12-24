package com.salach.journalhub.ui.screens.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.db.models.Note
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.enums.PageType
import com.salach.journalhub.repositories.JournalsRepository
import com.salach.journalhub.repositories.PagesRepository
import kotlinx.coroutines.launch

class PagesViewModel(private val repository: PagesRepository, private val journalRepository: JournalsRepository): ViewModel(){

    fun getPages(journalId: Int): LiveData<List<Page>> {
        return repository.getAllPages(journalId).asLiveData()
    }
    fun getJournal(journalId: Int): LiveData<Journal> {
        return journalRepository.getJournalById(journalId)
    }

    fun removeJournal(journal: Journal){
        viewModelScope.launch {
            journalRepository.removeJournal(journal)
        }
    }

    fun <T> loadPage(pageId: Long, type: PageType): LiveData<T>?{
        return repository.getFullRepresentation<T>(pageId, type)
    }

    fun saveNote(page: Page, note: Note){
        viewModelScope.launch {
            if(page.id == null){
                repository.insert(page, note, PageType.NOTE)
            } else {
                repository.update(page, note, PageType.NOTE)
            }
        }
    }
}

class PagesViewModelFactory(private val repository: PagesRepository, private val journalRepository: JournalsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PagesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PagesViewModel(repository, journalRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
