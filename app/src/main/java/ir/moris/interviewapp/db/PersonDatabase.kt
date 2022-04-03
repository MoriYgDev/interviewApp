package ir.moris.interviewapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.moris.interviewapp.ui.fragments.DatabaseFragment


@Database(entities = [Person::class], version = 1 , exportSchema = true)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun getPersonDao(): PersonDao

    companion object {
        @Volatile
        private var instance: PersonDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): PersonDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: createDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun createDatabase(context: Context): PersonDatabase {
            return Room.databaseBuilder(
                context.applicationContext, PersonDatabase::class.java, "person_db.db"
            ).createFromAsset("database/person_db.db").build()
        }
    }

}