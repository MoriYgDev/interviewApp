package ir.moris.interviewapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {

    @Query("Select * from table_person")
    fun getAll(): LiveData<List<Person>>

}