package com.salach.journalhub.ui.screens.dashboard
import androidx.lifecycle.*
import com.salach.journalhub.db.models.Goal
import com.salach.journalhub.repositories.DashboardRepository


class DashboardViewModel(repository: DashboardRepository): ViewModel(){
    var goals: LiveData<List<Goal>> = repository.getActiveGoals().asLiveData()
}


class DashboardViewModelFactory(private val repository: DashboardRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DashboardViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
