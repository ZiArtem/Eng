package com.example.myenglishapp
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Query("SELECT*FROM words_table ORDER BY id")
    fun readAllWord(): LiveData<List<Word>>
}