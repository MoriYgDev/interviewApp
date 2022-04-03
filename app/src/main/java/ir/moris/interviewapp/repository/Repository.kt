package ir.moris.interviewapp.repository

import androidx.lifecycle.LiveData
import ir.moris.interviewapp.db.Person
import ir.moris.interviewapp.db.PersonDatabase

class Repository(
    private val db : PersonDatabase
) {
    fun getAll() : LiveData<List<Person>>{
        return db.getPersonDao().getAll()
    }
}