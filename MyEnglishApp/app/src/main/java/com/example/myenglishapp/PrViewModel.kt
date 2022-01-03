package com.example.myenglishapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    init {
        val wordDao1 = PrDatabase.getDatabase(application).wordDao()

        repository = Repository(
            wordDao1
        )
    }

    //Word Functions

    fun addWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWord(word)
        }
    }

    fun deleteWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWord(word)
        }
    }

    fun updateWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWord(word)
        }
    }

    fun readAllWord(): LiveData<List<Word>> {
        return repository.readAllWord()
    }
}