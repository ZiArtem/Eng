package com.example.myenglishapp.repository

import android.R
import androidx.lifecycle.LiveData
import com.example.myenglishapp.dao.ListWordsDao
import com.example.myenglishapp.dao.WordDao
import com.example.myenglishapp.entities.ListWords
import com.example.myenglishapp.entities.Word

class Repository(
    private val wordDao: WordDao,
    private val listWordsDao: ListWordsDao
) {
    //Word Functions

    suspend fun addWord(word: Word) {
        wordDao.addWord(word)
    }

    suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(word)
    }

    suspend fun updateWord(word: Word) {
        wordDao.updateWord(word)
    }

    fun readAllWord(): LiveData<List<Word>> {
        return wordDao.readAllWord()
    }

    fun getWordByIdList(id_list: Int): LiveData<List<Word>>{
        return wordDao.getWordByIdList(id_list)
    }

    //ListWord Functions

    suspend fun addListWords(listWords: ListWords){
       listWordsDao.addListWords(listWords)
    }

    suspend fun deleteListWords(listWords: ListWords){
        listWordsDao.deleteListWords(listWords)
    }

    suspend fun updateListWords(listWords: ListWords){
        listWordsDao.updateListWords(listWords)
    }

    fun readAllListWords(): LiveData<List<ListWords>> {
        return  listWordsDao.readAllListWords()
    }
}