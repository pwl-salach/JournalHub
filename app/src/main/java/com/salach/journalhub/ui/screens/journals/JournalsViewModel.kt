package com.salach.journalhub.ui.screens.journals

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.salach.journalhub.db.models.Journal
import com.salach.journalhub.repositories.JournalsRepository
import kotlinx.coroutines.launch

class JournalsViewModel(private val repository: JournalsRepository) : ViewModel() {
    val journals: LiveData<List<Journal>> = repository.getJournals().asLiveData()

    fun addJournal(journal: Journal) {
        viewModelScope.launch {
            repository.insertJournal(journal)
        }
    }
}

class JournalsViewModelFactory(private val repository: JournalsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JournalsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JournalsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
