package ir.moris.interviewapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var firstname: String,
    var lastname: String,
    var age: Int = 0
) {

}