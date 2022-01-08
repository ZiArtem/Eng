package com.example.myenglishapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "wordlist_table"
)

data class ListWords(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: String
)