package com.example.myenglishapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.myenglishapp.viewModel.PrViewModel
import com.example.myenglishapp.entities.Word
import com.example.myenglishapp.WordActionListener
import com.example.myenglishapp.WordAdapter
import com.example.myenglishapp.databinding.HomeFragmentBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding
    private lateinit var adapter: WordAdapter
    private val mUserViewModel: PrViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = WordAdapter(object : WordActionListener {
            override fun deleteItem(word: Word) {
                lifecycleScope.launch {
                 mUserViewModel.deleteWord(word)
                }
            }
        })

        binding.recyclerViewWord.adapter= adapter

        binding.recyclerViewWord.layoutManager = LinearLayoutManager(binding.root.context)
        (binding.recyclerViewWord.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        mUserViewModel.readAllWord().observe(activity as LifecycleOwner, { words -> adapter.setData(words) })

        binding.button.setOnClickListener {
            lifecycleScope.launch {
                for (i in 1..100)
                    mUserViewModel.addWord(
                        Word(
                            0,
                            "Word or phrase" + (0..10000).random(),
                            "translation " + (0..100).random(),
                            -1
                        )
                    )
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}