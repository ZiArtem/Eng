package com.example.myenglishapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myenglishapp.entities.ListWords


@Dao
interface ListWordsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListWords(listWords: ListWords)

    @Delete
    suspend fun deleteListWords(listWords: ListWords)

    @Update
    suspend fun updateListWords(listWords: ListWords)

    @Query("SELECT*FROM wordlist_table  ORDER BY id")
    fun readAllListWords(): LiveData<List<ListWords>>
}