package ir.moris.interviewapp.ui

import androidx.lifecycle.ViewModel
import ir.moris.interviewapp.repository.Repository

class DbViewModel(
    val repository : Repository
) :  ViewModel(){
    fun getAll() = repository.getAll()
}