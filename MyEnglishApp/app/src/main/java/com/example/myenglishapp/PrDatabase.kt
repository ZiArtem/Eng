package com.example.myenglishapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    entities = [Word::class]
)

//@TypeConverters(DataConverter::class)
abstract class PrDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

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