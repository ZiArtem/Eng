package com.example.myenglishapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myenglishapp.dao.ListWordsDao
import com.example.myenglishapp.dao.WordDao
import com.example.myenglishapp.entities.ListWords
import com.example.myenglishapp.entities.Word

@Database(
    version = 3,
    entities = [Word::class, ListWords::class]
)

//@TypeConverters(DataConverter::class)
abstract class PrDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun listWordsDao(): ListWordsDao

    companion object {
        @Volatile
        private var INSTANCE: PrDatabase? = null

        fun getDatabase(context: Context): PrDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PrDatabase::class.java,
                    "database"
//                ).fallbackToDestructiveMigration().createFromAsset("bd/new.db").build()
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}