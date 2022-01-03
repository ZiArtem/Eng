package com.example.myenglishapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mUserViewModel: PrViewModel
    private lateinit var button: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(PrViewModel::class.java)

        adapter = WordAdapter(object : WordActionListener {
            override fun deleteItem(word: Word) {
                lifecycleScope.launch {
                 mUserViewModel.deleteWord(word)
                }
            }
        })

        recyclerView = findViewById(R.id.recyclerViewWord)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        mUserViewModel.readAllWord().observe(this, { words -> adapter.setData(words) })


        button = findViewById(R.id.button)
        button.setOnClickListener {

            lifecycleScope.launch {
                for (i in 1..100)
                    mUserViewModel.addWord(
                        Word(
                            0,
                            "Word or phrase" + (0..10000).random(),
                            "translation " + (0..100).random()
                        )
                    )
            }
        }
    }
}