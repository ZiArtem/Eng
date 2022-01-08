package com.example.myenglishapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myenglishapp.databinding.NewActivityMainBinding
import com.example.myenglishapp.entities.ListWords
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
        mUserViewModel.addListWords(ListWords(0,"My new test 13/10 test 1","this is date"))

    }
}