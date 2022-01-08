package com.example.myenglishapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myenglishapp.entities.Word

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Query("SELECT*FROM all_words_table WHERE id_list = -1 ORDER BY id")
    fun readAllWord(): LiveData<List<Word>>

    @Query("SELECT*FROM all_words_table WHERE id_list = (:id_list) ORDER BY id")
    fun getWordByIdList(id_list: Int): LiveData<List<Word>>

}