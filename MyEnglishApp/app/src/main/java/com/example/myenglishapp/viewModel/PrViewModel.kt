package com.example.myenglishapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myenglishapp.database.PrDatabase
import com.example.myenglishapp.repository.Repository
import com.example.myenglishapp.entities.ListWords
import com.example.myenglishapp.entities.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    init {
        val wordDao1 = PrDatabase.getDatabase(application).wordDao()
        val listWordsDao1 = PrDatabase.getDatabase(application).listWordsDao()

        repository = Repository(
            wordDao1,
            listWordsDao1
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

    //ListWord Functions

    fun addListWords(listWords: ListWords) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addListWords(listWords)
        }
    }

    fun deleteListWords(listWords: ListWords) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteListWords(listWords)
        }
    }

    fun updateListWords(listWords: ListWords) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateListWords(listWords)
        }
    }

    fun readAllListWords(): LiveData<List<ListWords>> {
        return repository.readAllListWords()
    }

}