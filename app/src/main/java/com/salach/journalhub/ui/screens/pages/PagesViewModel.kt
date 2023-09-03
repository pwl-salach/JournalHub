package com.salach.journalhub.ui.screens.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.salach.journalhub.db.models.Page
import com.salach.journalhub.repositories.PagesRepository

class PagesViewModel(repository: PagesRepository): ViewModel(){
    var pages: LiveData<List<Page>> = repository.getAllPages(0).asLiveData()
}

class PagesViewModelFactory(private val repository: PagesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PagesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PagesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
