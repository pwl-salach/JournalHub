package com.example.myapplication.ui.screens.dashboard
import androidx.lifecycle.*
import com.example.myapplication.db.models.Goal
import com.example.myapplication.repositories.DashboardRepository


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
