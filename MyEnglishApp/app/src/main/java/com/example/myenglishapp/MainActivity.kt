package com.example.myenglishapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myenglishapp.databinding.NewActivityMainBinding
import com.example.myenglishapp.entities.ListWords
import com.example.myenglishapp.entities.Word
import com.example.myenglishapp.fragments.HomeFragment
import com.example.myenglishapp.fragments.Fragment_2
import com.example.myenglishapp.fragments.ListsFragment
import com.example.myenglishapp.viewModel.PrViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: NewActivityMainBinding
    private lateinit var mUserViewModel: PrViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(PrViewModel::class.java)


        supportFragmentManager.beginTransaction().replace(binding.fragm1.id, HomeFragment.newInstance()).commit()
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(binding.fragm1.id, HomeFragment.newInstance()).commit()
                }
                R.id.fav -> {
                    supportFragmentManager.beginTransaction().replace(binding.fragm1.id, ListsFragment.newInstance()).commit()
                }
                R.id.fav1 -> {
                    supportFragmentManager.beginTransaction().replace(binding.fragm1.id, Fragment_2.newInstance()).commit()
                }
            }
            true
        }

        mUserViewModel.addListWords(ListWords(101,"тест 101 ","8 января"))
        mUserViewModel.addListWords(ListWords(0,"тест 8 ","8 января"))
        mUserViewModel.addWord(Word(0,"almost", "почти",101))
        mUserViewModel.addWord(Word(0,"aware", "осведомленный",101))
        mUserViewModel.addWord(Word(0,"certain", "определенный",101))
        mUserViewModel.addWord(Word(0,"complain", "жаловаться",101))
    }
}