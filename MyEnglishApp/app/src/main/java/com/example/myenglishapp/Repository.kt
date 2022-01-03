package com.example.myenglishapp

import androidx.lifecycle.LiveData

class Repository(
    private val wordDao: WordDao
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
}