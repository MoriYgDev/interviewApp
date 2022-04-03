package ir.moris.interviewapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.moris.interviewapp.repository.Repository

class DbViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DbViewModel(repository) as T
    }
}