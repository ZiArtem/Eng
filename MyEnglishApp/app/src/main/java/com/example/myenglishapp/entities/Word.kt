package com.example.myenglishapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "all_words_table"
)

data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String
)