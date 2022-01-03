package com.example.myenglishapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "words_table"
)

data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String
)